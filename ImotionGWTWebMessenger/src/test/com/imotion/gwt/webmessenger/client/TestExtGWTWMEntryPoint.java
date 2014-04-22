package test.com.imotion.gwt.webmessenger.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
			 
public class TestExtGWTWMEntryPoint implements EntryPoint {
	
	private static final String TOKEN_CHAT 			= "chat";
	private static final String TOKEN_MULTICHAT 	= "multichat";
	private static final String TOKEN_CONNECTION 	= "connectionTest";
	private static final String TOKEN_MESSAGE 		= "messagingTest";

	@Override
	public void onModuleLoad() {
		
		Composite container = null;
		String token = History.getToken();
		if (token != null && token.length() > 0) {
			if (token.equals(TOKEN_CHAT)) {
				container = new TestExtGWTWMChatRoom();
			} else if (token.equals(TOKEN_MULTICHAT)) {
				container = new TestExtGWTWMMultiChatRoom();
			} else if (token.equals(TOKEN_CONNECTION)) {
				container = new TestExtGWTWMConnectionTest();
			} else if (token.equals(TOKEN_MESSAGE)) {
				container = new TestExtGWTWMMessagingTest();
			} else {
				container = new TestExtGWTWMChatRoom();
			}
		} else {
			container = new TestExtGWTWMChatRoom();
		}
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(container);
	}
}
