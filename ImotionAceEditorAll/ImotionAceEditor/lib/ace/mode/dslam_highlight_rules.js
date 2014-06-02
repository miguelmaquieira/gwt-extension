define(function(require, exports, module) {
	"use strict";
	
	var oop = require("../lib/oop");
	var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;
	var ImolangHighlightRules = require("./imolang_highlight_rules").ImolangHighlightRules;
	
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
	    
	    ImolangHighlightRules.call(this);

	    dslamRules = {
	    	    start: [
	    	    	 {	token: keywordMapper,
	    	            regex: '[a-zA-Z][a-zA-Z-]*\\b'
	    	         }
	    	 	]
	    };

	    this.addRules(dslamRules, "dslam-");
	};
	
	DslamHighlightRules.metaData = {
			name: 'DSLAM',
			scopeName: 'source.dslam',
			fileTypes: [ 'dslam' ] 
	}
	
	oop.inherits(DslamHighlightRules, ImolangHighlightRules);
	
	exports.DslamHighlightRules = DslamHighlightRules;
});
