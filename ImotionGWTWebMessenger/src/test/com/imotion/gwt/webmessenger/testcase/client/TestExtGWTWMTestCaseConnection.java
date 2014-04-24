package test.com.imotion.gwt.webmessenger.testcase.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.imotion.gwt.webmessenger.client.ExtGWTWMException;
import com.imotion.gwt.webmessenger.client.ExtGWTWMFactory;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;

public class TestExtGWTWMTestCaseConnection extends Composite {

	private final TestExtGwtWMTestCaseTexts 	TEXTS 	= GWT.create(TestExtGwtWMTestCaseTexts.class);

	private ExtGWTWMCommCSConnection connectionCS;
	private Label statusLabel;


	public TestExtGWTWMTestCaseConnection() {

		FlowPanel contentPanel = new FlowPanel();
		contentPanel.addStyleName("extgwt-testCaseConnectionContentPanel");
		initWidget(contentPanel);

		Button open = new Button(TEXTS.button_conect_text());
		contentPanel.add(open);

		open.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				
				//Pedimos la conexión para un determinado roomId y userId
				ExtGWTWMCommCSConnection connection = getCommCS("defaultRoom", "defaultUser");
				if (connection != null) {
					//Abrimos la conexion
					connection.connect();
				} 
			}							
		});


		Button close = new Button(TEXTS.button_disconect_text());
		contentPanel.add(close);

		close.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				if (connectionCS != null) {
					//Cerramos la conexión
					ExtGWTWMFactory.getDefaultStandaloneCommCS().releaseConnection(connectionCS);
					connectionCS = null;
				}			
			}
		});

		statusLabel = new Label("Status: Conection closed");
		contentPanel.add(statusLabel);

	}


	/**********************************************************************
	 *                       PRIVATE FUNCTIONS 							*
	 **********************************************************************/

	private ExtGWTWMCommCSConnection getCommCS(String nickname, String roomname) {
		if (connectionCS == null) {
			try {
				connectionCS = ExtGWTWMFactory.getDefaultStandaloneCommCS().getConnection(roomname, nickname);
				
				//Añadimos el handler de apertura de la conexión
				connectionCS.getCommHandlerWrapper().addCommOpenHandler(new ExtGWTWMHasOpenCommHandler() {					
					@Override
					public void handleConnectionOpened() {
						String text = "Status: Conection open. userId: " 	+ connectionCS.getSessionData().getUserId() 
															+ ", roomId: " 	+ connectionCS.getSessionData().getRoomId();						
						statusLabel.setText(text);													
					}
				});

				//Añadimos el handler de cierre de la conexión
				connectionCS.getCommHandlerWrapper().addCommCloseHandler(new ExtGWTWMHasCloseCommHandler() {					
					@Override
					public void handleConnectionClosed() {
						String text = "Status: Conection closed";						
						statusLabel.setText(text);							
					}
				});

			} catch (ExtGWTWMException e) {
				statusLabel.setText(e.getMessage());
			}
		}
		return connectionCS;

	}
}
