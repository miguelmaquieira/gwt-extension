define(function(require, exports, module) {
"use strict";

var oop = require("../lib/oop");
var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;

var DslamHighlightRules = function() {
    // regexp must not have capturing parentheses. Use (?:) instead.
    // regexps are ordered -> the first match is used

    this.$rules = { 
    
    start: [
    	 { token: 'keyword.command.levelone',
           regex: '\\b(?!-)(?:cli|environment|configure|show|admin)(?!-)\\b',
           caseInsensitive: true },
         { token: 'keyword.command.leveltwo',
           regex: '\\b(?!-)(?:equipment|interface|vlan|pppox-relay|software-mngt)(?!-)\\b',
           caseInsensitive: true },
         { token: 'keyword.command.levelthree',
           regex: '\\b(?!-)(?:slot|protection-group|protection-element|admin-status|id|mode|name|protocol-filte|pppoe-relay-tag|circuit-id-pppoe|remote-id-pppoe|customer-id|shub|port|egress-port|cross-connect|engine|mac-addr-conc|unlock|database|save)(?!-)\\b',
           caseInsensitive: true },
         { token: 'keyword.control',
           regex: '\\bin\\b',
           caseInsensitive: true },
         { token: 'keyword.control.conditional',
           regex: '\\b(?:if|else)\\b',
           caseInsensitive: true },
         { token: 'keyword.control.repeat',
           regex: '\\b(for|while)\\b',
           caseInsensitive: true },
         { token: 'keyword.operator',
           regex: '\\b(?:EQU|NEQ|LSS|LEQ|GTR|GEQ)\\b' },
         { token: 'comment.line.colons',
           regex: '//.*$' },
         { include: 'variable' },
 	],
    variable: [
         	{ token: 'variable-local', regex: '\\$[a-zA-z_]+\\w*'},
         	{ token: 'variable-process', regex: '#[a-zA-z_]+\\w*'},
         	{ token: 'variable-extern', regex: '%[a-zA-z_]+\\w*'}
       	 ]  
    }
    
    this.normalizeRules();
};

DslamHighlightRules.metaData = { name: 'DSLAM',
      scopeName: 'source.dslam',
      fileTypes: [ 'dslam' ] }


oop.inherits(DslamHighlightRules, TextHighlightRules);

exports.DslamHighlightRules = DslamHighlightRules;
});
