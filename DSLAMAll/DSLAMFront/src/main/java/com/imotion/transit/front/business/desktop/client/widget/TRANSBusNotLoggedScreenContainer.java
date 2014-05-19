package com.imotion.transit.front.business.desktop.client.widget;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.transit.front.business.client.view.controller.TRANSI18NBusinessControllerTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.anchor.AEGWTImageAnchor;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabelStyled;
import com.selene.arch.exe.gwt.client.view.controller.AEGWTI18NControllerTexts;
import com.selene.arch.exe.gwt.mvp.event.flow.AEGWTFlowEvent;

public class TRANSBusNotLoggedScreenContainer extends AEGWTCompositePanel implements HasWidgets {

	public static final String NAME = "BusinessNotLoguedScreenContainer";
	
	private static final AEGWTI18NControllerTexts 		CONTROLLER_TEXTS 			= GWT.create(AEGWTI18NControllerTexts.class);
	private static final TRANSI18NBusinessControllerTexts 		BUSINESS_CONTROLLER_TEXTS 	= GWT.create(TRANSI18NBusinessControllerTexts.class);
	
	private static NotLoguedScreenContainerUiBinder uiBinder = GWT.create(NotLoguedScreenContainerUiBinder.class);

	interface NotLoguedScreenContainerUiBinder extends UiBinder<Widget, TRANSBusNotLoggedScreenContainer> {
	}

	@UiField VerticalPanel containerPanel;
	@UiField AEGWTImageAnchor logoAnchor; 
	@UiField HorizontalPanel hpLinksPanel;
	private AEGWTLabelStyled copyRight;
	private Anchor who;
	private Anchor where;
	private Anchor tos;
	private Anchor faq;
	private Anchor customerSupport;
	

	public TRANSBusNotLoggedScreenContainer() {
		initWidget(uiBinder.createAndBindUi(this));
		
		setSize("100%", "100%");
		
		hpLinksPanel.getElement().setAttribute("cellpadding", "10");
		copyRight = new AEGWTLabelStyled(BUSINESS_CONTROLLER_TEXTS.label_copyright_text());
		hpLinksPanel.add(copyRight);
		
		who = new Anchor(CONTROLLER_TEXTS.label_who_we_are_text());
		hpLinksPanel.add(who);
		
		where = new Anchor(CONTROLLER_TEXTS.label_where_we_are_text());
		hpLinksPanel.add(where);
		
		tos = new Anchor(CONTROLLER_TEXTS.label_terms_and_conditions_text());
		hpLinksPanel.add(tos);
		
		faq = new Anchor(CONTROLLER_TEXTS.label_faqs_text());
		hpLinksPanel.add(faq);
		
		customerSupport = new Anchor(CONTROLLER_TEXTS.label_contact_text());
		hpLinksPanel.add(customerSupport);
		
		logoAnchor.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				AEGWTFlowEvent flowEvt = new AEGWTFlowEvent(getWindowName(), getName(), null);
				getFlowEventHandlerManager().fireEvent(flowEvt);
				
			}
		});
	}

	public void setContent (Widget widget) {
		containerPanel.add(widget);
		containerPanel.setCellVerticalAlignment(widget, HasVerticalAlignment.ALIGN_MIDDLE);
		containerPanel.setCellHorizontalAlignment(widget, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	public void setContentSize(String width, String height) {
		containerPanel.setSize(width, height);
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// nothing to do
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void add(Widget widget) {
		setContent(widget);
	}

	@Override
	public void clear() {
		containerPanel.clear();
	}

	@Override
	public Iterator<Widget> iterator() {
		return containerPanel.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return containerPanel.remove(w);
	}
}
