package test.com.imotion.gwt.webmessenger.testcase.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.imotion.gwt.webmessenger.client.ExtGWTWMException;
import com.imotion.gwt.webmessenger.client.ExtGWTWMFactory;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection.TRANSPORT_TYPE;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError.TYPE;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;

public class TestExtGWTWMTestCaseConnectionTimeOut extends Composite {

	private final TestExtGwtWMTestCaseTexts 	TEXTS 	= GWT.create(TestExtGwtWMTestCaseTexts.class);

	private ExtGWTWMCommCSConnection connectionCS;
	private Label 				statusLabel;
	private Label 				errorLabel;
	private Label 				reconnectionsLabel;
	private TextBox 			timeoutTextbox;
	private TextBox				reconnectionAttemps;
	private int 				attemps;

	public TestExtGWTWMTestCaseConnectionTimeOut() {

		FlowPanel contentPanel = new FlowPanel();
		contentPanel.addStyleName("extgwt-testCaseConnectionContentPanel");
		initWidget(contentPanel);
		
		timeoutTextbox = new TextBox();
		timeoutTextbox.setSize("180px", "20px");
		timeoutTextbox.setText("timeout: Default value: 3 seg.");
		contentPanel.add(timeoutTextbox);
		
		reconnectionAttemps = new TextBox();
		reconnectionAttemps.setSize("180px", "20px");
		reconnectionAttemps.setText("Attemps: Default value: 3");
		contentPanel.add(reconnectionAttemps);

		Button open = new Button(TEXTS.button_conect_text());
		contentPanel.add(open);

		open.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				if (connectionCS == null) {
					attemps = 0;
					reconnectionsLabel.setText("Attemps: " + attemps);
					connect();
					Timer timerText = new Timer() {
						public void run() {
							if (connectionCS == null && getConnectionAttemps() > attemps) {
								attemps = attemps + 1;
								reconnectionsLabel.setText("Attemps: " + attemps);
								connect();
							} else {
								cancel();
							}
						}
					};
					timerText.scheduleRepeating((getTimeout() + 1) * 1000);
				}
			}							
		});


		Button close = new Button(TEXTS.button_disconect_text());
		contentPanel.add(close);

		close.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				if (connectionCS != null) {
					// Cerramos la conexión
					ExtGWTWMFactory.getDefaultStandaloneCommCS().releaseConnection(connectionCS);
					connectionCS = null;
				}	
			}
		});

		statusLabel = new Label();
		setStatusLabel("Status: Conection closed");
		contentPanel.add(statusLabel);
		
		errorLabel = new Label("Error: ");
		contentPanel.add(errorLabel);
		
		reconnectionsLabel = new Label("Attemps: 0");
		contentPanel.add(reconnectionsLabel);
	}


	/**********************************************************************
	 *                       PRIVATE FUNCTIONS 							*
	 **********************************************************************/

	private ExtGWTWMCommCSConnection getNewCommCS(final String roomname, final String nickname) {
		ExtGWTWMCommCSConnection conn = null;
		try {
			conn = ExtGWTWMFactory.getDefaultStandaloneCommCS().getConnection(roomname, nickname, 
																					  TRANSPORT_TYPE.LONG_POLLING,
																					  TRANSPORT_TYPE.STREAMING);
			
		} catch (ExtGWTWMException e) {
			setStatusLabel("Exception: " + e.getMessage());
		}
		return conn;
	}
	
	private void connect() {
		// Pedimos la conexión para un determinado roomId y userId
		final String username = "user1";
		final String roomname = "room1";
		final ExtGWTWMCommCSConnection connection = getNewCommCS(roomname, username);
		
		if (connection != null) {
			
			// Añadimos un handler de errores
			connection.getErrorHandlerWrapper().addErrorHandler(new ExtGWTWMHasErrorHandler() {
				
				@Override
				public void onError(ExtGWTWMError error) {
					errorLabel.setText("Error: " + error.toString());
					setStatusLabel("Status: Connection error");
					ExtGWTWMFactory.getDefaultStandaloneCommCS().releaseConnection(connection);
				}
				
				@Override
				public TYPE[] getErrorType() {
					return new TYPE[] { TYPE.CONNECTION_ERROR } ;
				}
			});
			
			//Añadimos el handler de apertura de la conexión
			connection.getCommHandlerWrapper().addCommOpenHandler(new ExtGWTWMHasOpenCommHandler() {					
				@Override
				public void handleConnectionOpened() {
					String text = "Status: Conection open. userId: " 	+ username 
														+ ", roomId: " 	+ roomname;
					setStatusLabel(text);
					connectionCS = connection;
					errorLabel.setText("Error: no errors");
				}
			});

			//Añadimos el handler de cierre de la conexión
			connection.getCommHandlerWrapper().addCommCloseHandler(new ExtGWTWMHasCloseCommHandler() {					
				@Override
				public void handleConnectionClosed() {
					connectionCS = null;
					setStatusLabel("Status: Conection closed");
					errorLabel.setText("Error: no error");
					
				}
			});
			
			connect(connection, getTimeout());
		}
	}
	
	
	private void connect(ExtGWTWMCommCSConnection connection, int timeout) {
		//System.out.println("Attemp: "+ attemps);
		int attemps = 1; //attemps + 1;
		setStatusLabel("Status: Trying connection. Attemp: " + attemps + "...");
		connection.connect(timeout);
	}
	
	private int getConnectionAttemps() {
		// Número de reintentos
		String attempsStr = reconnectionAttemps.getText();
		int connectionAttemps = 3;
		try {
			connectionAttemps = Integer.parseInt(attempsStr);
		} catch (NumberFormatException nfe) {
			
		}
		return connectionAttemps;
	}
	
	private int getTimeout() {
		// Connection timeout
		String timeoutStr = timeoutTextbox.getText();
		int timeout = 3;
		try {
			timeout = Integer.parseInt(timeoutStr);
		} catch (NumberFormatException nfe) {
			
		}
		return timeout;
	}
	
	private void setStatusLabel(String text) {
		statusLabel.setText(text);
	}
}
