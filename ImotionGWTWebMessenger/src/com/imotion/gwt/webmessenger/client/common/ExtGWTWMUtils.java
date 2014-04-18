package com.imotion.gwt.webmessenger.client.common;

public class ExtGWTWMUtils {
	
	public static String getStacktrace(Exception exception) {
		StackTraceElement[] traceArray = exception.getStackTrace();
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement trace: traceArray) {
			sb.append(trace.getClassName())
				.append(" : ")
				.append(trace.getLineNumber())
				.append(" : ")
				.append(trace.getMethodName())
				.append("\n");
		}
		return sb.toString();
	}
	
//	public static String getStacktrace(Exception ex) {
//		StackTraceElement[] traces = ex.getStackTrace();
//		String trace = "no trace";
//		if (traces != null) {
//			for (int i = 0; i < traces.length || i < 10; i++) {
//				trace = trace.concat(traces[i].toString());
//			}
//			trace.concat("\n...");
//		}
//		return trace;
//	}

}
