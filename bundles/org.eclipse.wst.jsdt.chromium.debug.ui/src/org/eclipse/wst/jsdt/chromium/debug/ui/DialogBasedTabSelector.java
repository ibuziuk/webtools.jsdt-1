// Copyright (c) 2009 The Chromium Authors. All rights reserved.
// This program and the accompanying materials are made available
// under the terms of the Eclipse Public License v1.0 which accompanies
// this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html

package org.eclipse.wst.jsdt.chromium.debug.ui;

import static org.eclipse.wst.jsdt.chromium.sdk.util.BasicUtil.getSafe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.wst.jsdt.chromium.debug.core.model.TabSelector;
import org.eclipse.wst.jsdt.chromium.debug.core.model.WipTabSelector;
import org.eclipse.wst.jsdt.chromium.sdk.Browser.TabConnector;
import org.eclipse.wst.jsdt.chromium.sdk.Browser.TabFetcher;
import org.eclipse.wst.jsdt.chromium.sdk.wip.WipBackend;
import org.eclipse.wst.jsdt.chromium.sdk.wip.WipBrowser;
import org.eclipse.wst.jsdt.chromium.sdk.wip.WipBrowser.WipTabConnector;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * A TabSelector which brings up a dialog allowing users to select which target
 * browser tab to debug.
 */
public class DialogBasedTabSelector {

  public static TabSelector INSTANCE = new Impl();
  public static WipTabSelector WIP_INSTANCE = new Wip();

  private static abstract class Base<CONNECTOR, SOURCE> {
    public CONNECTOR selectTab(SOURCE tabFetcher) throws IOException {
      List<? extends CONNECTOR> allTabs = getTabs(tabFetcher);

      List<CONNECTOR> filteredTabs = new ArrayList<CONNECTOR>(allTabs.size());

      for (CONNECTOR tab : allTabs) {
        if (!isAttached(tab)) {
          filteredTabs.add(tab);
        }
      }

      if (autoSelectSingleTab()) {
        if (allTabs.size() == 1 && filteredTabs.size() == 1) {
          // if all crystal clear -- choose by default
          // disable auto-select if there are some already attached tabs:
          //  user has already seen this dialog and might have got used to it
          //  he might not understand why it didn't show up this time
          return allTabs.get(0);
        }
      }

      final Map<Integer, CONNECTOR> map = new HashMap<Integer, CONNECTOR>();
      final List<String> urls = new ArrayList<String>(filteredTabs.size());
      for (int i = 0; i < filteredTabs.size(); ++i) {
        CONNECTOR connector = filteredTabs.get(i);
        map.put(i, connector);
        urls.add(getUrl(connector));
      }
      final List<CONNECTOR> result = new ArrayList<CONNECTOR>(1);
      Display.getDefault().syncExec(new Runnable() {
        public void run() {
          final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
          final ChromiumTabSelectionDialog dialog = new ChromiumTabSelectionDialog(shell, urls);
          dialog.setBlockOnOpen(true);
          int dialogResult = dialog.open();
          if (dialogResult == ChromiumTabSelectionDialog.OK) {
            result.add(getSafe(map, dialog.getSelectedLine()));
          }
          // otherwise (result[0] == null) which means "Do not attach"
        }
      });
      if (result.isEmpty()) {
        return null;
      } else {
        return result.get(0);
      }
    }

    protected abstract List<? extends CONNECTOR> getTabs(SOURCE tabFetcher) throws IOException;

    protected abstract boolean isAttached(CONNECTOR connector);
    protected abstract String getUrl(CONNECTOR connector);

    private boolean autoSelectSingleTab() {
      return true;
    }
  }

  private static class Impl extends Base<TabConnector, TabFetcher> implements TabSelector {
    @Override protected List<? extends TabConnector> getTabs(TabFetcher tabFetcher)
        throws IOException {
      return tabFetcher.getTabs();
    }

    @Override protected boolean isAttached(TabConnector connector) {
      return connector.isAlreadyAttached();
    }

    @Override protected String getUrl(TabConnector connector) {
      return connector.getUrl();
    }
  }

  private static class Wip extends Base<WipTabConnector, WipTabSelector.BrowserAndBackend>
      implements WipTabSelector {
    @Override
    protected List<? extends WipTabConnector> getTabs(
        WipTabSelector.BrowserAndBackend browserAndBackend) throws IOException {
      WipBrowser browser = browserAndBackend.getBrowser();
      WipBackend backend = browserAndBackend.getBackend();
      return browser.getTabs(backend);
    }

    @Override
    protected boolean isAttached(WipTabConnector connector) {
      return connector.isAlreadyAttached();
    }

    @Override
    protected String getUrl(WipTabConnector connector) {
      return connector.getUrl();
    }
  }
}
