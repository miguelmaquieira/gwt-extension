define('ace/mode/dslam', ['require', 'exports', 'module' , 'ace/lib/oop', 'ace/mode/text', 'ace/mode/dslam_highlight_rules', 'ace/mode/folding/cstyle'], function(require, exports, module) {


var oop = require("../lib/oop");
var TextMode = require("./text").Mode;
var DslamHighlightRules = require("./dslam_highlight_rules").DslamHighlightRules;
var FoldMode = require("./folding/cstyle").FoldMode;

var Mode = function() {
    this.HighlightRules = DslamHighlightRules;
    this.foldingRules = new FoldMode();
};
oop.inherits(Mode, TextMode);

(function() {
    this.lineCommentStart = "//";
    this.blockComment = "";
    this.$id = "ace/mode/dslam";
}).call(Mode.prototype);

exports.Mode = Mode;
});
define('ace/mode/dslam_highlight_rules', ['require', 'exports', 'module' , 'ace/lib/oop', 'ace/mode/text_highlight_rules'], function(require, exports, module) {


var oop = require("../lib/oop");
var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;

var DslamHighlightRules = function() {
	
	var levelone = ("cli|environment|configure|show|admin");

    var leveltwo = ("equipment|interface|vlan|pppox-relay|software-mngt");


    var levelthree = ("slot|protection-group|protection-element|admin-status|" +
    		"id|mode|name|protocol-filter|pppoe-relay-tag|circuit-id-pppoe|remote-id-pppoe|" +
    		"customer-id|shub|port|egress-port|cross-connect|engine|mac-addr-conc|unlock|database|save"
    );
    
    var commands = levelone + leveltwo + levelthree;

    var keywordMapper = this.createKeywordMapper({
        "keyword.command"	: commands,
    }, "default");
    
    var identifierExpression = "[a-zA-Z][a-zA-Z0-9_]*\\w*";

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
           regex: '\\b(?:==|!=|<|<=|>|>=|\\+|-|\\*|/|.)\\b' },
         { token: 'comment.line.colons',
           regex: '//.*$' },
         { token: 'keyword'				, regex: '(>|read|match|tag|rb)'},
         { token: 'variable-script'		, regex: '\\$' 	+ 	identifierExpression},
         { token: 'variable-process'	, regex: '#' 	+ 	identifierExpression},
         { token: 'variable-execution'	, regex: '@'	+ 	identifierExpression},
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

define('ace/mode/folding/cstyle', ['require', 'exports', 'module' , 'ace/lib/oop', 'ace/range', 'ace/mode/folding/fold_mode'], function(require, exports, module) {


var oop = require("../../lib/oop");
var Range = require("../../range").Range;
var BaseFoldMode = require("./fold_mode").FoldMode;

var FoldMode = exports.FoldMode = function(commentRegex) {
    if (commentRegex) {
        this.foldingStartMarker = new RegExp(
            this.foldingStartMarker.source.replace(/\|[^|]*?$/, "|" + commentRegex.start)
        );
        this.foldingStopMarker = new RegExp(
            this.foldingStopMarker.source.replace(/\|[^|]*?$/, "|" + commentRegex.end)
        );
    }
};
oop.inherits(FoldMode, BaseFoldMode);

(function() {

    this.foldingStartMarker = /(\{|\[)[^\}\]]*$|^\s*(\/\*)/;
    this.foldingStopMarker = /^[^\[\{]*(\}|\])|^[\s\*]*(\*\/)/;

    this.getFoldWidgetRange = function(session, foldStyle, row, forceMultiline) {
        var line = session.getLine(row);
        var match = line.match(this.foldingStartMarker);
        if (match) {
            var i = match.index;

            if (match[1])
                return this.openingBracketBlock(session, match[1], row, i);
                
            var range = session.getCommentFoldRange(row, i + match[0].length, 1);
            
            if (range && !range.isMultiLine()) {
                if (forceMultiline) {
                    range = this.getSectionRange(session, row);
                } else if (foldStyle != "all")
                    range = null;
            }
            
            return range;
        }

        if (foldStyle === "markbegin")
            return;

        var match = line.match(this.foldingStopMarker);
        if (match) {
            var i = match.index + match[0].length;

            if (match[1])
                return this.closingBracketBlock(session, match[1], row, i);

            return session.getCommentFoldRange(row, i, -1);
        }
    };
    
    this.getSectionRange = function(session, row) {
        var line = session.getLine(row);
        var startIndent = line.search(/\S/);
        var startRow = row;
        var startColumn = line.length;
        row = row + 1;
        var endRow = row;
        var maxRow = session.getLength();
        while (++row < maxRow) {
            line = session.getLine(row);
            var indent = line.search(/\S/);
            if (indent === -1)
                continue;
            if  (startIndent > indent)
                break;
            var subRange = this.getFoldWidgetRange(session, "all", row);
            
            if (subRange) {
                if (subRange.start.row <= startRow) {
                    break;
                } else if (subRange.isMultiLine()) {
                    row = subRange.end.row;
                } else if (startIndent == indent) {
                    break;
                }
            }
            endRow = row;
        }
        
        return new Range(startRow, startColumn, endRow, session.getLine(endRow).length);
    };

}).call(FoldMode.prototype);

});
