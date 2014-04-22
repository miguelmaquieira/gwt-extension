package test.com.imotion.gwt.webmessenger.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.imotion.gwt.webmessenger.client.ExtGWTWMFactory;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;

public class TestExtGWTWMConnectionTest extends Composite {

	private final TestExtGwtWMTexts 	TEXTS 	= GWT.create(TestExtGwtWMTexts.class);
	
	private final static String DEFAULT_USER_ID = "defaultUserId";
	private final static String DEFAULT_ROOM_ID = "defaultRoomId";
		
	private ExtGWTWMCommCSConnection connectionCS;
	private Label statusLabel;
	
		
	public TestExtGWTWMConnectionTest() {
		FlowPanel contentPanel = new FlowPanel();
		initWidget(contentPanel);
		
		Button open = new Button(TEXTS.button_conect_text());
		contentPanel.add(open);
		
		open.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				ExtGWTWMCommCSConnection connection = getCommCS(DEFAULT_USER_ID, DEFAULT_ROOM_ID);
				if (connection != null) {
					connection.connect();
				} else {
					Window.alert("No se ha podido iniciar comunicación con los parámetros: 'userNickName': " + DEFAULT_USER_ID + " ' roomId: '" + DEFAULT_ROOM_ID);
				}
			}
							
		});
		
	
		Button close = new Button(TEXTS.button_disconect_text());
		contentPanel.add(close);
		
		close.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (connectionCS != null) {
					connectionCS.disconnect();
					connectionCS = null;
				}			
			}
		});
		
		statusLabel = new Label(TEXTS.status_label_text() + " Closed");
		contentPanel.add(statusLabel);
		
	}
	
	
	private ExtGWTWMCommCSConnection getCommCS(String nickname, String roomname) {
		if (nickname == null || nickname.length() == 0 || roomname == null || roomname.length() == 0) {
			Window.alert("Debes informar: 'nickname' y 'roomname'");
			return null;
		} else  {
			if (connectionCS == null) {
				connectionCS = ExtGWTWMFactory.getDefaultStandaloneCommCS().getConnection(roomname, nickname);
			
				connectionCS.getCommHandlerWrapper().addCommOpenHandler(new ExtGWTWMHasOpenCommHandler() {			
					@Override
					public void handleConnectionOpened() {
						statusLabel.setText(TEXTS.status_label_text() + " Open. userId: " + DEFAULT_USER_ID + ", roomId: " + DEFAULT_ROOM_ID);						
					}
				});

				
				connectionCS.getCommHandlerWrapper().addCommCloseHandler(new ExtGWTWMHasCloseCommHandler() {				
					@Override
					public void handleConnectionClosed() {
						statusLabel.setText(TEXTS.status_label_text() + " Closed");						
					}
				});
			}
			return connectionCS;
		}
	}



}
