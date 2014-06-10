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

public class DSLAMVisitorImpl extends DSLAMBaseVisitor<Value> {

	private DSLAMIConnection		connection;
	private Map<String, Object>		allVariables;

	public DSLAMVisitorImpl(DSLAMIConnection connection, Map<String, Object> variables) {
		this.connection = connection;
		this.allVariables = new HashMap<>();
		if (variables != null) {
			allVariables.putAll(variables);
		}
	}

	@Override
	public Value visitExecution(@NotNull DSLAMParser.ExecutionContext ctx) {
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
		Value responseValue = new Value(response);

		return responseValue;
	}

	@Override
	public Value visitAssignStatement(@NotNull DSLAMParser.AssignStatementContext ctx) {
		String varName = ctx.VARIABLE_SCRIPT().getText();
		Value varValue = null;

		ExpressionContext	expression	= ctx.expression();
		ExecutionContext	execution	= ctx.execution();
		TerminalNode		literal		= ctx.STRING_LITERAL();
		if (expression != null) {
			varValue = this.visit(expression);
		} else if (execution != null) {
			varValue = this.visit(execution);
		} else if (literal != null) {
			String string = literal.getText();
			varValue = new Value(string);
		}

		allVariables.put(varName, varValue.asObject());

		return varValue;
	}

	@Override
	public Value visitIfStatement(@NotNull DSLAMParser.IfStatementContext ctx) {
		Value conditionValueResult = this.visit(ctx.condition());
		boolean conditionResult = conditionValueResult.asBoolean();

		if (conditionResult) {
			this.visit(ctx.ifBlock());
		} else if (ctx.elseBlock() != null) {
			this.visit(ctx.elseBlock());
		}

		return Value.VOID;
	}

	@Override
	public Value visitForStatement(@NotNull DSLAMParser.ForStatementContext ctx) {
		String iterVarName = ctx.VARIABLE_SCRIPT().getText();
		Value start = this.visit(ctx.integerValue(0));
		Value end = this.visit(ctx.integerValue(1));

		List<StatementContext> statementContextList = ctx.statement();
		allVariables.put(iterVarName, start.asInteger());
		for (int i = start.asInteger(); i <= end.asInteger(); i++) {
			allVariables.put(iterVarName, i);
			visitStamentContextList(statementContextList);
			i = (int) allVariables.get(iterVarName);
		}
		allVariables.remove(iterVarName);
		return Value.VOID;
	}

	@Override
	public Value visitWhileStatement(@NotNull DSLAMParser.WhileStatementContext ctx) {
		ConditionContext conditionContext = ctx.condition();
		List<StatementContext> statementContextList = ctx.statement();
		while (checkCondition(conditionContext)) {
			visitStamentContextList(statementContextList);
		}
		return Value.VOID;
	}

	@Override
	public Value visitCondition(@NotNull DSLAMParser.ConditionContext ctx) {
		boolean result = false;

		String	logicalComparator 	= ctx.LOGICAL_COMPARATOR().getText();
		Value	leftSideValue		= this.visit(ctx.integerValue(0));
		Value	rightSideValue		= this.visit(ctx.integerValue(1));
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
		Value resultValue = new Value(result);
		return resultValue;
	}

	@Override
	public Value visitIntegerValue(@NotNull DSLAMParser.IntegerValueContext ctx) {
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

		Value integerValue = new Value(integer);
		return integerValue; 
	}

	@Override
	public Value visitParExp(@NotNull DSLAMParser.ParExpContext ctx) {
		return this.visit(ctx.expression());
	}

	@Override 
	public Value visitAritOp(@NotNull DSLAMParser.AritOpContext ctx) {
		ExpressionContext 	leftExpresion	= ctx.left;
		ExpressionContext 	rightExpresion	= ctx.right;
		String 				operator 		= ctx.op.getText();

		Value 	leftValue 	= this.visit(leftExpresion);
		Value	rightValue	= this.visit(rightExpresion);
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
		Value valueResult = new Value(result);
		return valueResult; 
	}

	@Override 
	public Value visitAtomExpr(@NotNull DSLAMParser.AtomExprContext ctx) { 
		return this.visit(ctx.integerValue());
	}

	/**
	 * PRIVATE
	 */

	private boolean checkCondition(ConditionContext conditionContext) {
		Value conditionValueResult = this.visit(conditionContext);
		boolean conditionResult = conditionValueResult.asBoolean();
		return conditionResult;
	}

	private void visitStamentContextList(List<StatementContext> statementContextList) {
		for (StatementContext statement : statementContextList) {
			this.visit(statement);
		}
	}

}
