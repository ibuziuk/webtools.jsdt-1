/*******************************************************************************
 * Copyright (c) 2016 Red Hat, Inc. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * 	Contributors:
 * 		 Red Hat Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.wst.jsdt.js.node.internal.launch;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.wst.jsdt.chromium.ConnectionLogger;
import org.eclipse.wst.jsdt.chromium.debug.core.model.BreakpointSynchronizer;
import org.eclipse.wst.jsdt.chromium.debug.core.model.ConnectionLoggerImpl;
import org.eclipse.wst.jsdt.chromium.debug.core.model.ConsolePseudoProcess;
import org.eclipse.wst.jsdt.chromium.debug.core.model.DebugTargetImpl;
import org.eclipse.wst.jsdt.chromium.debug.core.model.JavascriptVmEmbedder;
import org.eclipse.wst.jsdt.chromium.debug.core.model.JavascriptVmEmbedderFactory;
import org.eclipse.wst.jsdt.chromium.debug.core.model.NamedConnectionLoggerFactory;
import org.eclipse.wst.jsdt.chromium.debug.core.model.SourceWrapSupport;
import org.eclipse.wst.jsdt.chromium.debug.core.model.VProjectWorkspaceBridge;
import org.eclipse.wst.jsdt.chromium.debug.core.model.WorkspaceBridge;
import org.eclipse.wst.jsdt.chromium.debug.core.model.SourceWrapSupport.Wrapper;
import org.eclipse.wst.jsdt.chromium.util.Destructable;
import org.eclipse.wst.jsdt.chromium.util.DestructingGuard;
import org.eclipse.wst.jsdt.js.node.internal.NodeConstants;

final public class NodeDebugConnector {
	
	final private ILaunchConfiguration configuration;
	final private ILaunch launch;
	
	public NodeDebugConnector(ILaunchConfiguration configuration, final ILaunch launch) {
		this.configuration = configuration;
		this.launch = launch;
	}

	boolean attach() throws CoreException{
		
		String host = configuration.getAttribute(NodeConstants.ATTR_HOST_FIELD, NodeConstants.DEFAULT_HOST);
		int port = Integer.parseInt(configuration
				.getAttribute(NodeConstants.ATTR_PORT_FIELD, String.valueOf(NodeConstants.DEFAULT_PORT)));

		NamedConnectionLoggerFactory consoleFactory = NO_CONNECTION_LOGGER_FACTORY;
		if (configuration.getAttribute(NodeConstants.ATTR_ADD_NETWORK_CONSOLE_FIELD, false)) {
			consoleFactory = new NamedConnectionLoggerFactory() {
				public ConnectionLogger createLogger(String title) {
					return createConsoleAndLogger(launch, true, title);
				}
			};
		}
		      JavascriptVmEmbedder.ConnectionToRemote remoteServer = JavascriptVmEmbedderFactory.connectToStandalone(host, port, consoleFactory);
		      remoteServer.selectVm();
		      DestructingGuard destructingGuard = new DestructingGuard();
		        Destructable lauchDestructor = new Destructable() {
		          public void destruct() {
		            if (!launch.hasChildren()) {
		              DebugPlugin.getDefault().getLaunchManager().removeLaunch(launch);
		            }
		          }
		        };

		        destructingGuard.addValue(lauchDestructor);

		        WorkspaceBridge.Factory bridgeFactory =
		            new VProjectWorkspaceBridge.FactoryImpl(configuration.getName());

		        final DebugTargetImpl target =
		            new DebugTargetImpl(launch, bridgeFactory, new SourceWrapSupport(new ArrayList<Wrapper>()),  BreakpointSynchronizer.Direction.MERGE);

		        Destructable targetDestructor = new Destructable() {
		          public void destruct() {
		          }
		        };
		        destructingGuard.addValue(targetDestructor);


		        boolean attached = DebugTargetImpl.attach(target, remoteServer, destructingGuard, 
		            new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
						}
					}, new NullProgressMonitor());
		        if (!attached) {
		          // Cancel pressed.
		          return false;
		        }
		        
		        launch.addDebugTarget(target);
		        return true;
		
	}

	static final NamedConnectionLoggerFactory NO_CONNECTION_LOGGER_FACTORY = new NamedConnectionLoggerFactory() {
		public ConnectionLogger createLogger(String title) {
			return null;
		}
	};
	
	static ConnectionLogger createConsoleAndLogger(final ILaunch launch, final boolean addLaunchToManager,
			final String title) {
		final ConsolePseudoProcess.Retransmitter consoleRetransmitter = new ConsolePseudoProcess.Retransmitter();

		// This controller is responsible for creating ConsolePseudoProcess only
		// on
		// logStarted call. Before this ConnectionLoggerImpl with all it fields
		// should stay
		// garbage-collectible, because connection may not even start.
		ConnectionLoggerImpl.LogLifecycleListener consoleController = new ConnectionLoggerImpl.LogLifecycleListener() {
			private final AtomicBoolean alreadyStarted = new AtomicBoolean(false);

			public void logClosed() {
				consoleRetransmitter.processClosed();
			}

			public void logStarted(ConnectionLoggerImpl connectionLogger) {
				boolean res = alreadyStarted.compareAndSet(false, true);
				if (!res) {
					throw new IllegalStateException();
				}
				ConsolePseudoProcess consolePseudoProcess = new ConsolePseudoProcess(launch, title,
						consoleRetransmitter, connectionLogger.getConnectionTerminate());
				consoleRetransmitter.startFlushing();
				if (addLaunchToManager) {
					// Active the launch (again if it has already been removed)
					DebugPlugin.getDefault().getLaunchManager().addLaunch(launch);
				}
			}
		};

		return new ConnectionLoggerImpl(consoleRetransmitter, consoleController);
	}
}
