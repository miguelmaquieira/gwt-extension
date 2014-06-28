package com.imotion.dslam.antlr;

import java.util.ArrayList;
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
import com.imotion.antlr.DSLAMParser.ListExpContext;
import com.imotion.antlr.DSLAMParser.ListItemContext;
import com.imotion.antlr.DSLAMParser.StatementContext;
import com.imotion.antlr.DSLAMParser.StringExprContext;
import com.imotion.antlr.DSLAMParser.ValueContext;
import com.imotion.antlr.DSLAMParser.VariableContext;
import com.imotion.dslam.conn.CRONIOIConnection;
import com.imotion.dslam.conn.CRONIOIExecutionData;

public class DSLAMInterpreterVisitorImpl extends DSLAMBaseVisitor<DSLAMInterpreterVisitorValue> implements CRONIOILangVisitor {

	private CRONIOIConnection		connection;
	private Map<String, Object>		allVariables;

	public DSLAMInterpreterVisitorImpl(CRONIOIConnection connection, Map<String, Object> variables) {
		this.connection = connection;
		this.allVariables = new HashMap<>();
		if (variables != null) {
			allVariables.putAll(variables);
		}
	}
	
	/**
	 * CRONIOILangVisitor
	 */
	
	@Override
	public void pause() throws InterruptedException {
		
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Visitor methods
	 */

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

		CRONIOIExecutionData response = connection.executeCommand(finalCommand);
		DSLAMInterpreterVisitorValue responseValue = new DSLAMInterpreterVisitorValue(response);

		return responseValue;
	}

	@Override
	public DSLAMInterpreterVisitorValue visitAssignStatement(@NotNull DSLAMParser.AssignStatementContext ctx) {
		String varName = ctx.VARIABLE_SCRIPT().getText();
		DSLAMInterpreterVisitorValue varValue = null;

		ExpressionContext			expression			= ctx.expression();
		StringExprContext			stringExpression	= ctx.stringExpr();
		ListExpContext				listExpContext 		= ctx.listExp();
		ExecutionContext			execution			= ctx.execution();
		if (expression != null) {
			if (expression.getChildCount() == 1) {
				ParseTree firstChild = expression.getChild(0);
				varValue = this.visit(firstChild);
			} else {
				varValue = this.visit(expression);
			}
		} else if (stringExpression != null) {
			varValue =this.visit(stringExpression);
		} else if (listExpContext != null) {
			varValue =this.visit(listExpContext);
		} else if (execution != null) {
			varValue = this.visit(execution);
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
		DSLAMInterpreterVisitorValue start = this.visit(ctx.value(0));
		DSLAMInterpreterVisitorValue end = this.visit(ctx.value(1));

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
	
	@SuppressWarnings("unchecked")
	@Override
	public DSLAMInterpreterVisitorValue visitForEachStatement(@NotNull DSLAMParser.ForEachStatementContext ctx) {
		String							listVarName				= ctx.variable().getText();
		String							itemListVarName			= ctx.VARIABLE_SCRIPT().getText();
		List<StatementContext>			statementContextList	= ctx.statement();
		List<Object> 					objectList 				= (List<Object>) allVariables.get(listVarName);
		for (Object object : objectList) {
			allVariables.put(itemListVarName, object);
			visitStamentContextList(statementContextList);
		}
		allVariables.remove(itemListVarName);
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
		DSLAMInterpreterVisitorValue	leftSideValue		= this.visit(ctx.value(0));
		DSLAMInterpreterVisitorValue	rightSideValue		= this.visit(ctx.value(1));
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
	
	@SuppressWarnings("unchecked")
	@Override
	public DSLAMInterpreterVisitorValue visitValue(@NotNull DSLAMParser.ValueContext ctx) {
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
		}else if (variableContext != null) {
			String variableName = variableContext.getText();
			object = allVariables.get(variableName);
		} else if (listItemContext != null) {
			VariableContext					variableListContext = listItemContext.variable();
			ValueContext					indexValueContext	= listItemContext.value();
			String							variableName		= variableListContext.getText();
			List<Object>					list				= (List<Object>) allVariables.get(variableName);
			DSLAMInterpreterVisitorValue	indexValue			= this.visit(indexValueContext);
			object	= list.get(indexValue.asInteger());
		}
		
		if (object instanceof String) {
			String objectAsString = (String) object;
			objectAsString = objectAsString.replace("\"", "");
			object = objectAsString;
		}

		DSLAMInterpreterVisitorValue value = new DSLAMInterpreterVisitorValue(object);
		return value; 
	}
	
	@Override 
	public DSLAMInterpreterVisitorValue visitStringExpr(@NotNull DSLAMParser.StringExprContext ctx) {
		List<ValueContext> stringValueContextList = ctx.value();
		StringBuilder sb = new StringBuilder();
		for (ValueContext stringValueContext : stringValueContextList) {
			DSLAMInterpreterVisitorValue stringValue = this.visit(stringValueContext);
			String string = stringValue.asString(); 
			sb.append(string);
		}
		String result = sb.toString();
		DSLAMInterpreterVisitorValue returnValue = new DSLAMInterpreterVisitorValue(result);
		return returnValue; 
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
		return this.visit(ctx.value());
	}
	
	@Override
	public DSLAMInterpreterVisitorValue visitListExp(@NotNull DSLAMParser.ListExpContext ctx) {
		List<ValueContext> valueContextList = ctx.value();
		List<Object> list = new ArrayList<>();
		for (ValueContext valueContext : valueContextList) {
			DSLAMInterpreterVisitorValue value = this.visit(valueContext);
			list.add(value.asObject());
		}
		DSLAMInterpreterVisitorValue listValue = new DSLAMInterpreterVisitorValue(list);
		return listValue; 
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
