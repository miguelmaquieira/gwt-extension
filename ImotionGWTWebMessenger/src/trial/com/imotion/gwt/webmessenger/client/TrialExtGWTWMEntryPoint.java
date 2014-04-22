package trial.com.imotion.gwt.webmessenger.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
			 
public class TrialExtGWTWMEntryPoint implements EntryPoint {
	
	private static final String TOKEN_CONNECTION 	= "test1";
	private static final String TOKEN_MESSAGE 		= "test2";
	private static final String TOKEN_EXCEPTION 	= "test3";

	@Override
	public void onModuleLoad() {
		
		Composite container = null;
		String token = History.getToken();
		if (token != null && token.length() > 0) {
			if (token.equals(TOKEN_CONNECTION)) {
				container = new TrialExtGWTWMConnectionTest();
			} else if (token.equals(TOKEN_MESSAGE)) {
				container = new TrialExtGWTWMMessagingTest();
			} else if (token.equals(TOKEN_EXCEPTION)) {
				container = new TrialExtGWTWMConnectionExceptionTest();
			} else {
				container = new TrialExtGWTWMConnectionTest();
			}
		} else {
			container = new TrialExtGWTWMConnectionTest();
		}
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(container);
	}
}
