define('ace/snippets/dslam', ['require', 'exports', 'module' ], function(require, exports, module) {


exports.snippetText = "## Commands\n\
snippet if\n\
	if (${1}) {\n\
		${2}//TODO:\n\
	}\n\
\n\
snippet else {\n\
	else {\n\
		${1}//TODO:\n\
	}\n\
\n\
snippet else if\n\
	else if (${1}) {\n\
		${2}//TODO:\n\
	}\n\
\n\
snippet for\n\
	for ${1} in (${2} .. ${3}) {\n\
		${4}//TODO:\n\
	}\n\
\n\
snippet foreach\n\
	foreach (${1} : ${2}) {\n\
		${3}//TODO:\n\
	}\n\
\n\
snippet while\n\
	while (${1}) {\n\
		${2}//TODO:\n\
	}\n\
snippet sendCommand\n\
	sendCommand(\"${1}\");\n\
	\n\
snippet readUntil\n\
	readUntil(\"${1}\");";
exports.scope = "dslam";

});
