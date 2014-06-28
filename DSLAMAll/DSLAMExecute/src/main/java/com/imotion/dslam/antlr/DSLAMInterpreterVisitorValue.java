package com.imotion.dslam.antlr;

import java.util.List;

import com.imotion.dslam.conn.CRONIOIExecutionData;

public class DSLAMInterpreterVisitorValue {

	public static DSLAMInterpreterVisitorValue VOID = new DSLAMInterpreterVisitorValue(new Object());

	private final Object value;

	public DSLAMInterpreterVisitorValue(Object value) {
		this.value = value;
	}

	public Integer asInteger() {
		return (Integer) value;
	}

	public String asString() {
		return (String) value;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> asList() {
		return (List<Object>) value;
	}

	public CRONIOIExecutionData asResponse() {
		return (CRONIOIExecutionData) value;
	}

	public Boolean asBoolean() {
		return (Boolean) value;
	}

	public Object asObject() {
		return value;
	}

	public boolean isInteger() {
		return value instanceof Double;
	}

	public boolean isString() {
		return value instanceof String;
	}

	public boolean isBoolean() {
		return value instanceof Boolean;
	}

	public boolean isResponse() {
		return value instanceof CRONIOIExecutionData;
	}

	@Override
	public int hashCode() {

		if(value == null) {
			return 0;
		}

		return this.value.hashCode();
	}

	@Override
	public boolean equals(Object o) {

		if(value == o) {
			return true;
		}

		if(value == null || o == null || o.getClass() != value.getClass()) {
			return false;
		}

		DSLAMInterpreterVisitorValue that = (DSLAMInterpreterVisitorValue)o;

		return this.value.equals(that.value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}