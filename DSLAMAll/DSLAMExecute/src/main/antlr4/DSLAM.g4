grammar DSLAM;

import imolang;

//PARSER
program :	statement+; 
statement   :	assignStatement
			|   ifStatement
			|	whileStatement
			|	forStatement
			|	forEachStatement
			| 	execution;
			
assignStatement: VARIABLE_SCRIPT '=' (( (expression | stringExpr | listExp | execution) ';'));
			 
execution : 'execute' (dslamCommands | variable)+ ';';
			
dslamCommands : 	'cli'|'environment'|'configure'|'show'|'admin'|'equipment'
					|'interface'|'vlan'|'pppox-relay'|'software-mngt'
					|'slot'|'protection-group'|'protection-element'|'admin-status'
    				|'id'|'mode'|'name'|'protocol-filter'|'pppoe-relay-tag'|'circuit-id-pppoe'
    				|'remote-id-pppoe'|'customer-id'|'shub'|'port'|'egress-port'|'cross-connect'
    				|'engine'|'mac-addr-conc'|'unlock'|'database'|'save';
