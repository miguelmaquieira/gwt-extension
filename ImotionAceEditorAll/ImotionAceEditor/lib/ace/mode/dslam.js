define(function(require, exports, module) {
"use strict";

var oop = require("../lib/oop");
var TextMode = require("./text").Mode;
var DslamHighlightRules = require("./dslam_highlight_rules").DslamHighlightRules;
var FoldMode = require("./folding/cstyle").FoldMode;
var ImoLangMode = require("./imolang").Mode;

var Mode = function() {
    this.HighlightRules = DslamHighlightRules;
    this.foldingRules = new FoldMode();
    this.createModeDelegates({
        "imo-": ImoLangMode
    });
};
oop.inherits(Mode, ImoLangMode);

(function() {
    this.lineCommentStart = "//";
    this.blockComment = "";
    this.$id = "ace/mode/dslam";
}).call(Mode.prototype);

exports.Mode = Mode;
});
