package test.com.imotion.gwt.webmessenger.testcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
			 
public class TestExtGWTWMTestCaseEntryPoint implements EntryPoint {
	
	private static final String TOKEN_CONNECTION 	= "test1";
	private static final String TOKEN_MESSAGE 		= "test2";
	private static final String TOKEN_EXCEPTION 	= "test3";
	private static final String TOKEN_CONN_TO 		= "test4";

	@Override
	public void onModuleLoad() {
		
		Composite container = null;
		String token = History.getToken();
		if (token != null && token.length() > 0) {
			if (token.equals(TOKEN_CONNECTION)) {
				container = new TestExtGWTWMTestCaseConnection();
			} else if (token.equals(TOKEN_MESSAGE)) {
				container = new TestExtGWTWMTestCaseMessaging();
			} else if (token.equals(TOKEN_EXCEPTION)) {
				container = new TestExtGWTWMTestCaseConnectionError();
			} else if (token.equals(TOKEN_CONN_TO)) {
				container = new TestExtGWTWMTestCaseConnectionTimeOut();
			} else {
				container = new TestExtGWTWMTestCaseConnection();
			}
		} else {
			container = new TestExtGWTWMTestCaseConnection();
		}
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(container);
	}
}
