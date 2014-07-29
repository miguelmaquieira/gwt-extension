package com.imotion.dslam.antlr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.imotion.antlr.ImoLangBaseVisitor;
import com.imotion.antlr.ImoLangParser;
import com.imotion.antlr.ImoLangParser.ConditionContext;
import com.imotion.antlr.ImoLangParser.ExpressionContext;
import com.imotion.antlr.ImoLangParser.FunctionContext;
import com.imotion.antlr.ImoLangParser.ListExpContext;
import com.imotion.antlr.ImoLangParser.ListItemContext;
import com.imotion.antlr.ImoLangParser.ProgramContext;
import com.imotion.antlr.ImoLangParser.RbCaseDefaultContext;
import com.imotion.antlr.ImoLangParser.RbCaseItemContext;
import com.imotion.antlr.ImoLangParser.StatementContext;
import com.imotion.antlr.ImoLangParser.StringExprContext;
import com.imotion.antlr.ImoLangParser.ValueContext;
import com.imotion.antlr.ImoLangParser.VariableContext;
import com.imotion.dslam.conn.CRONIOIConnection;
import com.imotion.dslam.conn.CRONIOIExecutionData;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOInterpreterVisitorImpl extends ImoLangBaseVisitor<CRONIOInterpreterVisitorValue> implements CRONIOILangVisitor {

	private final String LAST_RESPONSE_KEY		= "@RESPONSE";
	private final String LAST_PROMPT_KEY		= "@PROMPT";
	private final String LAST_CMD_KEY			= "@CMD";
	private final String READ_KEY				= "@READ";
	private final String MATCH_KEY				= "@MATCH";
	private final String ROLLBACK_TAG_KEY		= "@TAG";

	private CRONIOIConnection				connection;
	private Map<String, Object>				allVariables;
	private CRONIOInterpreterVisitorImpl 	rollbackVisitor;
	private ProgramContext					rollbackTree;
	private String							rollbackConditionRegEx;

	private boolean stopExecution;

	public CRONIOInterpreterVisitorImpl(CRONIOIConnection connection, Map<String, Object> variables, String rollbackConditionRegEx, CRONIOInterpreterVisitorImpl rollbackVisitor, ProgramContext rollbackTree) {
		this.connection				= connection;
		this.allVariables			= variables;
		this.rollbackConditionRegEx	= rollbackConditionRegEx;
		this.rollbackVisitor		= rollbackVisitor;
		this.rollbackTree			= rollbackTree;
	}

	/**
	 * CRONIOILangVisitor
	 */

	@Override
	public void pauseExecution() throws InterruptedException {

	}

	@Override
	public void nextInstruction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopExecution() {
		stopExecution = true;
	}

	@Override
	public void continueExecution() {
		stopExecution = false;
	}

	/**
	 * Visitor methods
	 */

	@Override
	public CRONIOInterpreterVisitorValue visitExecution(@NotNull ImoLangParser.ExecutionContext ctx) {
		CRONIOInterpreterVisitorValue	commandValue 	= this.visit(ctx.stringExpr());
		CRONIOIExecutionData			response 		= connection.executeCommand(commandValue.asString());
		CRONIOInterpreterVisitorValue	responseValue 	= new CRONIOInterpreterVisitorValue(response);
		allVariables.put(LAST_RESPONSE_KEY	, response.getResponse());
		allVariables.put(LAST_PROMPT_KEY	, response.getPrompt());
		allVariables.put(LAST_CMD_KEY		, response.getSourceCommand());

		if (rollbackVisitor != null && !AEMFTCommonUtilsBase.isEmptyString(rollbackConditionRegEx)) {
			Pattern	pattern = Pattern.compile(rollbackConditionRegEx);
			Matcher	matcher = pattern.matcher(response.getResponse());
			if (matcher.find()) {
				stopExecution();
				rollbackVisitor.visit(rollbackTree);
			}
		}

		return responseValue;
	}

	@Override
	public CRONIOInterpreterVisitorValue visitExecutionWithoutResponse(@NotNull ImoLangParser.ExecutionWithoutResponseContext ctx) {
		CRONIOInterpreterVisitorValue	commandValue 	= this.visit(ctx.stringExpr());
		CRONIOIExecutionData			response 		= connection.executeCommandWithoutRead(commandValue.asString());
		allVariables.put(LAST_CMD_KEY, commandValue);
		CRONIOInterpreterVisitorValue	responseValue 	= new CRONIOInterpreterVisitorValue(response);
		return responseValue;
	}

	@Override
	public CRONIOInterpreterVisitorValue visitReadUntil(@NotNull ImoLangParser.ReadUntilContext ctx) {
		CRONIOInterpreterVisitorValue	regExValue		= this.visit(ctx.stringExpr());
		String							regExValueStr	= regExValue.asString().trim();
		String							read	 		= connection.readUntil(regExValueStr);
		allVariables.put(READ_KEY, read);
		CRONIOInterpreterVisitorValue	readValue 		= new CRONIOInterpreterVisitorValue(read);
		return readValue;
	}

	@Override
	public CRONIOInterpreterVisitorValue visitMatch(@NotNull ImoLangParser.MatchContext ctx) {
		CRONIOInterpreterVisitorValue	regExValue			= this.visit(ctx.stringExpr());
		String							regExValueStr		= regExValue.asString();
		String							lastResponse		= (String) allVariables.get(LAST_RESPONSE_KEY);
		//TODO read about AWK
		Pattern	pattern = Pattern.compile(regExValueStr);
		Matcher	matcher = pattern.matcher(lastResponse);
		List<String> matchGroups = new ArrayList<>();
		if (matcher.matches() || matcher.find()) {
			MatchResult  matchResult = matcher.toMatchResult();
			for (int i = 0; i <= matchResult.groupCount(); i++) {
				String matchGroupResult = matchResult.group(i);
				matchGroups.add(matchGroupResult);
			}
		}
		allVariables.put(MATCH_KEY, matchGroups);
		CRONIOInterpreterVisitorValue	matchGroupsData = new CRONIOInterpreterVisitorValue(matchGroups);
		return matchGroupsData;
	}

	@Override
	public CRONIOInterpreterVisitorValue visitRollback(@NotNull ImoLangParser.RollbackContext ctx) {
		stopExecution();
		if (rollbackVisitor != null) {
			CRONIOInterpreterVisitorValue tagValue = this.visit(ctx.stringExpr());
			allVariables.put(ROLLBACK_TAG_KEY, tagValue.asString());
			rollbackVisitor.visit(rollbackTree);
		}
		return CRONIOInterpreterVisitorValue.VOID;
	}

	@Override
	public CRONIOInterpreterVisitorValue visitTagBlockCode(@NotNull ImoLangParser.TagBlockCodeContext ctx) {
		CRONIOInterpreterVisitorValue tagValue = this.visit(ctx.stringExpr());
		allVariables.put(ROLLBACK_TAG_KEY, tagValue.asString());
		return CRONIOInterpreterVisitorValue.VOID;
	}

	@Override
	public CRONIOInterpreterVisitorValue visitAssignStatement(@NotNull ImoLangParser.AssignStatementContext ctx) {
		String varName = ctx.VARIABLE_SCRIPT().getText();
		CRONIOInterpreterVisitorValue varValue = null;

		ExpressionContext			expression			= ctx.expression();
		StringExprContext			stringExpression	= ctx.stringExpr();
		ListExpContext				listExpContext 		= ctx.listExp();
		FunctionContext				function			= ctx.function();
		if (expression != null) {
			if (expression.getChildCount() == 1) {
				ParseTree firstChild = expression.getChild(0);
				varValue = this.visit(firstChild);
			} else {
				varValue = this.visit(expression);
			}
		} else if (stringExpression != null) {
			varValue = this.visit(stringExpression);
		} else if (listExpContext != null) {
			varValue = this.visit(listExpContext);
		} else if (function != null) {
			varValue = this.visit(function);
		}

		allVariables.put(varName, varValue.asObject());

		return varValue;
	}

	@Override
	public CRONIOInterpreterVisitorValue visitIfStatement(@NotNull ImoLangParser.IfStatementContext ctx) {
		CRONIOInterpreterVisitorValue conditionValueResult = this.visit(ctx.condition());
		boolean conditionResult = conditionValueResult.asBoolean();

		if (conditionResult) {
			this.visit(ctx.ifBlock());
		} else if (ctx.ifStatement() != null) {
			this.visit(ctx.ifStatement());
		} else if (ctx.elseBlock() != null) {
			this.visit(ctx.elseBlock());
		}

		return CRONIOInterpreterVisitorValue.VOID;
	}
	
	@Override
	public CRONIOInterpreterVisitorValue visitRbCase(@NotNull ImoLangParser.RbCaseContext ctx) {
		List<RbCaseItemContext> caseList	= ctx.rbCaseItem();
		RbCaseDefaultContext 	caseDefault = ctx.rbCaseDefault();
		
		if (!AEMFTCommonUtilsBase.isEmptyList(caseList)) {
			boolean rbTagProcessed = false;
			for (RbCaseItemContext caseItem : caseList) {
				CRONIOInterpreterVisitorValue tagValue = this.visit(caseItem.stringExpr());
				String tagValueStr 	= tagValue.asString();
				String lastTag		= (String) allVariables.get(ROLLBACK_TAG_KEY);
				if (lastTag.equals(tagValueStr)) {
					List<StatementContext> statements = caseItem.statement();
					if (!AEMFTCommonUtilsBase.isEmptyList(statements)) {
						visitStamentContextList(statements);
					}
				}
			}
			
			if (!rbTagProcessed && caseDefault != null) {
				List<StatementContext> statements = caseDefault.statement();
				if (!AEMFTCommonUtilsBase.isEmptyList(statements)) {
					visitStamentContextList(statements);
				}
			}
		}
		
		return CRONIOInterpreterVisitorValue.VOID;
	}

	@Override
	public CRONIOInterpreterVisitorValue visitForStatement(@NotNull ImoLangParser.ForStatementContext ctx) {
		String iterVarName = ctx.VARIABLE_SCRIPT().getText();
		CRONIOInterpreterVisitorValue start = this.visit(ctx.value(0));
		CRONIOInterpreterVisitorValue end = this.visit(ctx.value(1));

		List<StatementContext> statementContextList = ctx.statement();
		allVariables.put(iterVarName, start.asInteger());
		for (int i = start.asInteger(); i <= end.asInteger(); i++) {
			allVariables.put(iterVarName, i);
			visitStamentContextList(statementContextList);
			i = (int) allVariables.get(iterVarName);
		}
		allVariables.remove(iterVarName);
		return CRONIOInterpreterVisitorValue.VOID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CRONIOInterpreterVisitorValue visitForEachStatement(@NotNull ImoLangParser.ForEachStatementContext ctx) {
		String							listVarName				= ctx.variable().getText();
		String							itemListVarName			= ctx.VARIABLE_SCRIPT().getText();
		List<StatementContext>			statementContextList	= ctx.statement();
		List<Object> 					objectList 				= (List<Object>) allVariables.get(listVarName);
		for (Object object : objectList) {
			allVariables.put(itemListVarName, object);
			visitStamentContextList(statementContextList);
		}
		allVariables.remove(itemListVarName);
		return CRONIOInterpreterVisitorValue.VOID;
	}

	@Override
	public CRONIOInterpreterVisitorValue visitWhileStatement(@NotNull ImoLangParser.WhileStatementContext ctx) {
		ConditionContext conditionContext = ctx.condition();
		List<StatementContext> statementContextList = ctx.statement();
		while (checkCondition(conditionContext)) {
			visitStamentContextList(statementContextList);
		}
		return CRONIOInterpreterVisitorValue.VOID;
	}

	@Override
	public CRONIOInterpreterVisitorValue visitCondition(@NotNull ImoLangParser.ConditionContext ctx) {
		boolean result = false;

		String							logicalComparator 	= ctx.LOGICAL_COMPARATOR().getText();
		CRONIOInterpreterVisitorValue	leftSideValue		= this.visit(ctx.value(0));
		CRONIOInterpreterVisitorValue	rightSideValue		= this.visit(ctx.value(1));
		int								leftSideInt			= leftSideValue.isInteger() ? leftSideValue.asInteger() : -1;
		int								rightSideInt		= rightSideValue.isInteger() ? rightSideValue.asInteger() : -1;

		if (logicalComparator.equals("==")) {
			result = leftSideValue.equals(rightSideValue);
		} else if (logicalComparator.equals("<=")) {
			result = leftSideInt <= rightSideInt;
		} else if (logicalComparator.equals(">=")) {
			result = leftSideInt >= rightSideInt;
		} else if (logicalComparator.equals("<")) {
			result = leftSideInt < rightSideInt;
		} else if (logicalComparator.equals(">")) {
			result = leftSideInt > rightSideInt;
		} else if (logicalComparator.equals("!=")) {
			result = leftSideInt != rightSideInt;
		}
		CRONIOInterpreterVisitorValue resultValue = new CRONIOInterpreterVisitorValue(result);
		return resultValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CRONIOInterpreterVisitorValue visitValue(@NotNull ImoLangParser.ValueContext ctx) {
		TerminalNode 			integerNode				= ctx.INTEGER();
		TerminalNode 			stringNode				= ctx.STRING_LITERAL();
		VariableContext 		variableContext			= ctx.variable();
		ListItemContext 		listItemContext 		= ctx.listItem();

		Object object = null;
		if (integerNode != null) {
			String integerStr = integerNode.getText();
			object = new Integer(integerStr);
		} else if (stringNode != null) {
			object = stringNode.getText();
		} else if (variableContext != null) {
			String variableName = variableContext.getText();
			object = allVariables.get(variableName);
		} else if (listItemContext != null) {
			VariableContext					variableListContext = listItemContext.variable();
			ValueContext					indexValueContext	= listItemContext.value();
			String							variableName		= variableListContext.getText();
			List<Object>					list				= (List<Object>) allVariables.get(variableName);
			CRONIOInterpreterVisitorValue	indexValue			= this.visit(indexValueContext);
			object	= list.get(indexValue.asInteger());
		}

		if (object instanceof String) {
			String objectAsString = (String) object;
			objectAsString = objectAsString.replace("\"", "");
			object = objectAsString;
		}

		CRONIOInterpreterVisitorValue value = new CRONIOInterpreterVisitorValue(object);
		return value; 
	}

	@Override 
	public CRONIOInterpreterVisitorValue visitStringExpr(@NotNull ImoLangParser.StringExprContext ctx) {
		List<ValueContext> stringValueContextList = ctx.value();
		StringBuilder sb = new StringBuilder();
		for (ValueContext stringValueContext : stringValueContextList) {
			CRONIOInterpreterVisitorValue stringValue = this.visit(stringValueContext);
			String string = stringValue.asString(); 
			sb.append(string);
		}
		String result = sb.toString();
		CRONIOInterpreterVisitorValue returnValue = new CRONIOInterpreterVisitorValue(result);
		return returnValue; 
	}

	@Override
	public CRONIOInterpreterVisitorValue visitParExp(@NotNull ImoLangParser.ParExpContext ctx) {
		return this.visit(ctx.expression());
	}

	@Override 
	public CRONIOInterpreterVisitorValue visitAritOp(@NotNull ImoLangParser.AritOpContext ctx) {
		ExpressionContext 	leftExpresion	= ctx.left;
		ExpressionContext 	rightExpresion	= ctx.right;
		String 				operator 		= ctx.op.getText();

		CRONIOInterpreterVisitorValue 	leftValue 	= this.visit(leftExpresion);
		CRONIOInterpreterVisitorValue	rightValue	= this.visit(rightExpresion);
		Integer result		= -1;
		if (operator.equals("*")) {
			result = leftValue.asInteger() * rightValue.asInteger();
		} else if (operator.equals("/")) {
			result = leftValue.asInteger() / rightValue.asInteger();
		} else if (operator.equals("+")) {
			result = leftValue.asInteger() + rightValue.asInteger();
		} else if (operator.equals("-")) {
			result = leftValue.asInteger() - rightValue.asInteger();
		}
		CRONIOInterpreterVisitorValue valueResult = new CRONIOInterpreterVisitorValue(result);
		return valueResult; 
	}

	@Override 
	public CRONIOInterpreterVisitorValue visitAtomExpr(@NotNull ImoLangParser.AtomExprContext ctx) { 
		return this.visit(ctx.value());
	}

	@Override
	public CRONIOInterpreterVisitorValue visitListExp(@NotNull ImoLangParser.ListExpContext ctx) {
		List<ValueContext> valueContextList = ctx.value();
		List<Object> list = new ArrayList<>();
		for (ValueContext valueContext : valueContextList) {
			CRONIOInterpreterVisitorValue value = this.visit(valueContext);
			list.add(value.asObject());
		}
		CRONIOInterpreterVisitorValue listValue = new CRONIOInterpreterVisitorValue(list);
		return listValue; 
	}

	/**
	 * PROTECTED
	 */

	protected boolean shouldVisitNextChild(@NotNull RuleNode node, CRONIOInterpreterVisitorValue currentResult) {
		return !stopExecution;
	}

	/**
	 * PRIVATE
	 */

	private boolean checkCondition(ConditionContext conditionContext) {
		CRONIOInterpreterVisitorValue conditionValueResult = this.visit(conditionContext);
		boolean conditionResult = conditionValueResult.asBoolean();
		return conditionResult;
	}

	private void visitStamentContextList(List<StatementContext> statementContextList) {
		for (StatementContext statement : statementContextList) {
			this.visit(statement);
		}
	}

}
