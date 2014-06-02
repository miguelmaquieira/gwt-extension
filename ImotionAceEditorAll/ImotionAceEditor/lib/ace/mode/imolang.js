define(function(require, exports, module) {
"use strict";

var oop = require("../lib/oop");
var TextMode = require("./text").Mode;
var ImolangHighlightRules = require("./imolang_highlight_rules").ImolangHighlightRules;
var FoldMode = require("./folding/cstyle").FoldMode;

var Mode = function() {
    this.HighlightRules = ImolangHighlightRules;
    this.foldingRules = new FoldMode();
};
oop.inherits(Mode, TextMode);

(function() {
    this.lineCommentStart = "//";
    this.blockComment = "";
    this.$id = "ace/mode/imolang";
}).call(Mode.prototype);

exports.Mode = Mode;
});
