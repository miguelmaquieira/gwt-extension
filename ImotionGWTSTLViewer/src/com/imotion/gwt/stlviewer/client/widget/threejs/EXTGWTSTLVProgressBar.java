package com.imotion.gwt.stlviewer.client.widget.threejs;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EXTGWTSTLVProgressBar extends VerticalPanel {

	private int elements = 20;
	  
	private int progress = 0;
	  
	private FlexTable barFrame;
	private Grid elementGrid;
	private Label textLabel;
	
	public EXTGWTSTLVProgressBar(int elements){
        this(elements, false);
    }
	  
	public EXTGWTSTLVProgressBar (int elements, boolean showText) {
		addStyleName("viewer-progressbar");
	    
	    // Set element count
	    this.elements = elements;
	    
	    // Styling
	    textLabel = new Label();
	    textLabel.setStyleName("progressbar-text");
	    
	    // Initialize the progress elements
	    elementGrid = new Grid(1, elements);
	    elementGrid.setStyleName("progressbar-inner");
	    elementGrid.setCellPadding(0);
	    elementGrid.setCellSpacing(0);
	    
	    for (int loop = 0; loop < elements; loop++) {
	    	Grid elm = new Grid(1, 1);
	    	elm.setHTML(0, 0, "");
	    	elm.setStyleName("progressbar-blankbar");
	    	elm.addStyleName("progressbar-bar");
	    	elementGrid.setWidget(0, loop, elm);
	    }
	    
	    // Create the container around the elements
	    Grid containerGrid = new Grid(1,1);
	    containerGrid.setCellPadding(0);
	    containerGrid.setCellSpacing(0);
	    containerGrid.setWidget(0, 0, elementGrid);
	    containerGrid.setStyleName("progressbar-outer");
	      
	    // Set up the surrounding flex table based on the options
	    barFrame = new FlexTable();
	    barFrame.setWidget(0, 0, textLabel);
	    barFrame.setWidget(0, 1, containerGrid);
	    barFrame.setWidth("100%");
	    
	    // Add the frame to the panel
	    add(barFrame);
	    
	    // Initialize progress bar
	    setProgress(0);
	}
	
	public void setProgress (int percentage) {
	    // Make sure we are error-tolerant
	    if (percentage > 100) {
	    	percentage = 100;
	    }
	    if (percentage < 0) {
	    	percentage = 0;
	    }
	    
	    // Set the internal variable
	    progress = percentage;
	    
	    // Update the elements in the progress grid to reflect the status
	    int completed = elements * percentage / 100;
	    for (int loop = 0; loop < elements; loop++) {
	    	Grid elm = (Grid) elementGrid.getWidget(0, loop);
	    	if (loop < completed) {
	    		elm.setStyleName("progressbar-fullbar");
	    		elm.addStyleName("progressbar-bar");
	    	} else {
	    		elm.setStyleName("progressbar-blankbar");
	    		elm.addStyleName("progressbar-bar");
	        }
	    }
	}

	public int getProgress () {
		return (progress);
	}
	
	public String getText () {
		return this.textLabel.getText();
	}
	public void setText (String text) {
		this.textLabel.setText(text);
	}
}
