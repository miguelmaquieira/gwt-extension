package test.com.imotion.gwt.webmessenger.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class TestExtGWTWMMultiChatRoom extends Composite {
	
	private HorizontalPanel buttonZone;
	private DeckPanel deckPanel;
	
	public TestExtGWTWMMultiChatRoom() {
		FlowPanel contentPanel = new FlowPanel();
		initWidget(contentPanel);
		contentPanel.addStyleName("extgwt-webMessengerMultiChatRoomContainer");
		
		/// Switch button panel
		buttonZone = new HorizontalPanel();
		buttonZone.addStyleName("extgwt-webMessengerMultiChatRoomButtonZone");
		buttonZone.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		buttonZone.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		contentPanel.add(buttonZone);
		
		//// Button chat1
		Anchor btChat1 = new Anchor("Room 1");
		buttonZone.add(btChat1);
		btChat1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showChat(0);
			}
		});
		
		//// Button chat2
		Anchor btChat2 = new Anchor("Room 2");
		buttonZone.add(btChat2);
		btChat2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showChat(1);
			}
		});
		
		/// Switch content panel
		deckPanel = new DeckPanel();
		deckPanel.addStyleName("extgwt-webMessengerMultiChatRoomContentZone");
		contentPanel.add(deckPanel);
		
		//// Chat1
		SimplePanel chatRoom1Zone = new SimplePanel();
		TestExtGWTWMChatRoom chatroom1 = new TestExtGWTWMChatRoom();
		chatroom1.setChatRoomId("room1");
		chatroom1.addStyleName("extgwt-webMessengerMultiChatRoomContentZoneChat");
		chatRoom1Zone.setWidget(chatroom1);
		deckPanel.add(chatRoom1Zone);
		
		
		//// Chat2
		SimplePanel chatRoom2Zone = new SimplePanel();
		TestExtGWTWMChatRoom chatroom2 = new TestExtGWTWMChatRoom();
		chatroom2.setChatRoomId("room2");
		chatroom2.addStyleName("extgwt-webMessengerMultiChatRoomContentZoneChat");
		chatRoom2Zone.setWidget(chatroom2);
		deckPanel.add(chatRoom2Zone);
		
		showChat(0);
	}
	
	/**********************************************************************
	 *                		   PRIVATE FUNCTIONS						  *
	 **********************************************************************/
	private void showChat(int index) {
		deckPanel.showWidget(index);
		int buttonCount = buttonZone.getWidgetCount();
		for (int i = 0; i < buttonCount; i++) {
			Widget button = buttonZone.getWidget(i);
			if (i == index) {
				button.addStyleName("extgwt-anchorSelected");
			} else {
				button.removeStyleName("extgwt-anchorSelected");
			}
		}
	}
}
