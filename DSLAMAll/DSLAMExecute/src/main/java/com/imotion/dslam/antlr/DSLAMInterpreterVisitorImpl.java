package com.imotion.dslam.antlr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.imotion.antlr.DSLAMBaseVisitor;
import com.imotion.antlr.DSLAMParser;
import com.imotion.antlr.DSLAMParser.ConditionContext;
import com.imotion.antlr.DSLAMParser.DslamCommandsContext;
import com.imotion.antlr.DSLAMParser.ExecutionContext;
import com.imotion.antlr.DSLAMParser.ExpressionContext;
import com.imotion.antlr.DSLAMParser.StatementContext;
import com.imotion.antlr.DSLAMParser.VariableContext;
import com.imotion.dslam.comm.DSLAMIConnection;
import com.imotion.dslam.comm.DSLAMIResponse;

public class DSLAMInterpreterVisitorImpl extends DSLAMBaseVisitor<DSLAMInterpreterVisitorValue> {

	private DSLAMIConnection		connection;
	private Map<String, Object>		allVariables;

	public DSLAMInterpreterVisitorImpl(DSLAMIConnection connection, Map<String, Object> variables) {
		this.connection = connection;
		this.allVariables = new HashMap<>();
		if (variables != null) {
			allVariables.putAll(variables);
		}
	}

	@Override
	public DSLAMInterpreterVisitorValue visitExecution(@NotNull DSLAMParser.ExecutionContext ctx) {
		String finalCommand = "";

		int statementMembersSize = ctx.getChildCount();
		for (int i = 1; i < statementMembersSize; i++) {
			if (i > 1) {
				finalCommand += " ";
			}
			
			ParseTree member = ctx.getChild(i);
			if (member instanceof DslamCommandsContext) {
				finalCommand += member.getText();
			} else if (member instanceof VariableContext) {
				String variableName = member.getText();
				Object value = allVariables.get(variableName);
				finalCommand += value.toString();
			}
		}

		DSLAMIResponse response = connection.executeCommand(finalCommand);
		DSLAMInterpreterVisitorValue responseValue = new DSLAMInterpreterVisitorValue(response);

		return responseValue;
	}

	@Override
	public DSLAMInterpreterVisitorValue visitAssignStatement(@NotNull DSLAMParser.AssignStatementContext ctx) {
		String varName = ctx.VARIABLE_SCRIPT().getText();
		DSLAMInterpreterVisitorValue varValue = null;

		ExpressionContext	expression	= ctx.expression();
		ExecutionContext	execution	= ctx.execution();
		TerminalNode		literal		= ctx.STRING_LITERAL();
		if (expression != null) {
			varValue = this.visit(expression);
		} else if (execution != null) {
			varValue = this.visit(execution);
		} else if (literal != null) {
			String string = literal.getText();
			varValue = new DSLAMInterpreterVisitorValue(string);
		}

		allVariables.put(varName, varValue.asObject());

		return varValue;
	}

	@Override
	public DSLAMInterpreterVisitorValue visitIfStatement(@NotNull DSLAMParser.IfStatementContext ctx) {
		DSLAMInterpreterVisitorValue conditionValueResult = this.visit(ctx.condition());
		boolean conditionResult = conditionValueResult.asBoolean();

		if (conditionResult) {
			this.visit(ctx.ifBlock());
		} else if (ctx.elseBlock() != null) {
			this.visit(ctx.elseBlock());
		}

		return DSLAMInterpreterVisitorValue.VOID;
	}

	@Override
	public DSLAMInterpreterVisitorValue visitForStatement(@NotNull DSLAMParser.ForStatementContext ctx) {
		String iterVarName = ctx.VARIABLE_SCRIPT().getText();
		DSLAMInterpreterVisitorValue start = this.visit(ctx.integerValue(0));
		DSLAMInterpreterVisitorValue end = this.visit(ctx.integerValue(1));

		List<StatementContext> statementContextList = ctx.statement();
		allVariables.put(iterVarName, start.asInteger());
		for (int i = start.asInteger(); i <= end.asInteger(); i++) {
			allVariables.put(iterVarName, i);
			visitStamentContextList(statementContextList);
			i = (int) allVariables.get(iterVarName);
		}
		allVariables.remove(iterVarName);
		return DSLAMInterpreterVisitorValue.VOID;
	}

	@Override
	public DSLAMInterpreterVisitorValue visitWhileStatement(@NotNull DSLAMParser.WhileStatementContext ctx) {
		ConditionContext conditionContext = ctx.condition();
		List<StatementContext> statementContextList = ctx.statement();
		while (checkCondition(conditionContext)) {
			visitStamentContextList(statementContextList);
		}
		return DSLAMInterpreterVisitorValue.VOID;
	}

	@Override
	public DSLAMInterpreterVisitorValue visitCondition(@NotNull DSLAMParser.ConditionContext ctx) {
		boolean result = false;

		String	logicalComparator 	= ctx.LOGICAL_COMPARATOR().getText();
		DSLAMInterpreterVisitorValue	leftSideValue		= this.visit(ctx.integerValue(0));
		DSLAMInterpreterVisitorValue	rightSideValue		= this.visit(ctx.integerValue(1));
		int		leftSideInt			= leftSideValue.asInteger();
		int		rightSideInt		= rightSideValue.asInteger();

		if (logicalComparator.equals("==")) {
			result = leftSideInt == rightSideInt;
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
		DSLAMInterpreterVisitorValue resultValue = new DSLAMInterpreterVisitorValue(result);
		return resultValue;
	}

	@Override
	public DSLAMInterpreterVisitorValue visitIntegerValue(@NotNull DSLAMParser.IntegerValueContext ctx) {
		VariableContext variableContext	= ctx.variable();
		TerminalNode 	integerNode		= ctx.INTEGER();

		Integer integer = null;
		if (variableContext != null) {
			String variableName = variableContext.getText();
			integer = (Integer) allVariables.get(variableName);
		} else {
			String integerStr = integerNode.getText();
			integer = new Integer(integerStr);
		}

		DSLAMInterpreterVisitorValue integerValue = new DSLAMInterpreterVisitorValue(integer);
		return integerValue; 
	}

	@Override
	public DSLAMInterpreterVisitorValue visitParExp(@NotNull DSLAMParser.ParExpContext ctx) {
		return this.visit(ctx.expression());
	}

	@Override 
	public DSLAMInterpreterVisitorValue visitAritOp(@NotNull DSLAMParser.AritOpContext ctx) {
		ExpressionContext 	leftExpresion	= ctx.left;
		ExpressionContext 	rightExpresion	= ctx.right;
		String 				operator 		= ctx.op.getText();

		DSLAMInterpreterVisitorValue 	leftValue 	= this.visit(leftExpresion);
		DSLAMInterpreterVisitorValue	rightValue	= this.visit(rightExpresion);
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
		DSLAMInterpreterVisitorValue valueResult = new DSLAMInterpreterVisitorValue(result);
		return valueResult; 
	}

	@Override 
	public DSLAMInterpreterVisitorValue visitAtomExpr(@NotNull DSLAMParser.AtomExprContext ctx) { 
		return this.visit(ctx.integerValue());
	}

	/**
	 * PRIVATE
	 */

	private boolean checkCondition(ConditionContext conditionContext) {
		DSLAMInterpreterVisitorValue conditionValueResult = this.visit(conditionContext);
		boolean conditionResult = conditionValueResult.asBoolean();
		return conditionResult;
	}

	private void visitStamentContextList(List<StatementContext> statementContextList) {
		for (StatementContext statement : statementContextList) {
			this.visit(statement);
		}
	}

}
