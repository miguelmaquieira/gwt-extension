define(function(require, exports, module) {
"use strict";

var oop = require("../lib/oop");
var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;

var ImolangHighlightRules = function() {
    // regexp must not have capturing parentheses. Use (?:) instead.
    // regexps are ordered -> the first match is used
	
	var keywords = ("IN|IF|ELSE|FOR|WHILE|EQU|NEQ|LSS|LEQ|GTR|GEQ");

    var keywordMapper = this.createKeywordMapper({
        "keyword": keywords,
    }, "default");

    this.$rules = { 
	    start: [
	         { token: 'comment.line.colons', 	regex: '//.*$' },
	         { token: 'variable-local', 	 	regex: '\\$[a-zA-Z_$][a-zA-Z0-9-]*\\w*'},
	         { token: 'variable-process',		regex: '#[a-zA-Z_$][a-zA-Z0-9-]*\\w*'},
	         { token: 'variable-extern', 		regex: '%[a-zA-Z_$][a-zA-Z0-9-]*\\w*'},
	         { token: keywordMapper,			regex: '[a-zA-Z_$][a-zA-Z0-9]*\\b', caseInsensitive: true}
	 	]
    };
    
    this.normalizeRules();
};

ImolangHighlightRules.metaData = { name: 'IMOLANG',
      scopeName: 'source.imolang',
      fileTypes: [ 'imolang' ] }


oop.inherits(ImolangHighlightRules, TextHighlightRules);

exports.ImolangHighlightRules = ImolangHighlightRules;
});
