define(function(require, exports, module) {
"use strict";

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
