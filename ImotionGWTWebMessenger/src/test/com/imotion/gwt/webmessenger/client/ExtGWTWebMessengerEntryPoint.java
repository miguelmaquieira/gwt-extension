package test.com.imotion.gwt.webmessenger.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
			 
public class ExtGWTWebMessengerEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(new ExtGWTWebMessengerChat());
	}
}
