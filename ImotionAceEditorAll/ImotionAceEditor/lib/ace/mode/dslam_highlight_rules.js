define(function(require, exports, module) {
"use strict";

var oop = require("../lib/oop");
var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;

var DslamHighlightRules = function() {
    // regexp must not have capturing parentheses. Use (?:) instead.
    // regexps are ordered -> the first match is used
	
	var levelone = ("cli|environment|configure|show|admin");

    var leveltwo = ("equipment|interface|vlan|pppox-relay|software-mngt");


    var levelthree = ("slot|protection-group|protection-element|admin-status|" +
    		"id|mode|name|protocol-filter|pppoe-relay-tag|circuit-id-pppoe|remote-id-pppoe|" +
    		"customer-id|shub|port|egress-port|cross-connect|engine|mac-addr-conc|unlock|database|save"
    );

    var keywordMapper = this.createKeywordMapper({
        "keyword.levelone": levelone,
        "keyword.leveltwo": leveltwo,
        "keyword.levelthree": levelthree
    }, "default");

    this.$rules = { 
    
    start: [
    	 { token: 'keyword.control',
           regex: '\\bin\\b',
         },
         { token: 'keyword.control.conditional',
           regex: '\\b(?:if|else)\\b',
         },
         { token: 'keyword.control.repeat',
           regex: '\\b(for|while|foreach)\\b',
         },
         { token: 'keyword.operator',
           regex: '\\b(?:==|!=|<|<=|>|>=)\\b' },
         { token: 'comment.line.colons',
           regex: '//.*$' },
         { token: 'variable-local', regex: '\\$[a-zA-Z][a-zA-Z0-9_]*\\w*'},
         { token: 'variable-process', regex: '#[a-zA-Z][a-zA-Z0-9_]*\\w*'},
         { token: 'variable-extern', regex: '%[a-zA-Z][a-zA-Z0-9_]*\\w*'},
         { token: keywordMapper,
            regex: '[a-zA-Z_$][a-zA-Z0-9-]*\\b',
            caseInsensitive: true 
         }
 	]
    };
    
    this.normalizeRules();
};

DslamHighlightRules.metaData = { name: 'DSLAM',
      scopeName: 'source.dslam',
      fileTypes: [ 'dslam' ] }


oop.inherits(DslamHighlightRules, TextHighlightRules);

exports.DslamHighlightRules = DslamHighlightRules;
});
