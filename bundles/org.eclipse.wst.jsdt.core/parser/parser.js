var acorn = require('acorn');
var loose = require("acorn/dist/acorn_loose");
var fs = require('fs');

var Main = function () {
       function Main() {
         this.buffer="";
       }
       Main.prototype.run = function () {
           var _this = this;
           var readline = require("readline");
           var rl = readline.createInterface(process.stdin, process.stdout);
           rl.on("line", function (line) {
               if(line === 'END'){
                 _this.processRequest();
              }else{
                _this.buffer += line;
              }
           });
           rl.on("close", function () {
               process.exit(0);
           });
       };

       Main.prototype.processRequest = function () {
           try {
               var options =  {"locations":true,
                                "range":true};
              //  var result = acorn.parse(this.buffer, options);
              var result = loose.parse_dammit(this.buffer, options);
               if (result === undefined) {
                   result = null;
               }
               var resultJson = JSON.stringify(result);
               console.log("RESULT: " + resultJson);
           }
           catch (e) {
               var error;
               if (e.stack != null) {
                   error = e.stack;
               }
               else if (e.message != null) {
                   error = e.message;
               }
               else {
                   error = "Error: No stack trace or error message was provided.";
               }
               console.log("ERROR: " + error.replace(/\n/g, "\\n"));
           }
       };
       return Main;
   }();

var main = new Main();
main.run();
