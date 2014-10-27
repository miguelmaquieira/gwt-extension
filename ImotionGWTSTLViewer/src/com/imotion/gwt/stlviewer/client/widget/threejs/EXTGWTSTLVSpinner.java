package com.imotion.gwt.stlviewer.client.widget.threejs;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class EXTGWTSTLVSpinner {
	
	private static final int INITIAL_SPEED = 3;
	
	private final Image decrementArrow = new Image();
	private final Image incrementArrow = new Image();

	private List<EXTGWTSTLVSpinnerListener> spinnerListeners = new ArrayList<EXTGWTSTLVSpinnerListener>();
	  
	private int step, minStep, maxStep, initialSpeed = 7;
	private long value, min, max;
	private boolean increment;
	private boolean constrained;

	private final Timer timer = new Timer() {
		private int counter = 0;
		private int speed = 7;
		  
		@Override
		public void cancel() {
			super.cancel();
			speed = initialSpeed;
			counter = 0;
		}
		  
		@Override
		public void run() {
			counter++;
			if (speed <= 0 || counter % speed == 0) {
				speed--;
				counter = 0;
				if (increment) {
					increase();
				} else {
					decrease();
				}
			}
			if (speed < 0 && step < maxStep) {
				step += 1;
			}
		}
	};
	
	public EXTGWTSTLVSpinner(EXTGWTSTLVSpinnerListener spinner, long value) {
		this(spinner, value, 0, 0, 1, 99, false);
	}

	public EXTGWTSTLVSpinner(EXTGWTSTLVSpinnerListener spinner, long value, long min, long max) {
		this(spinner, value, min, max, 1, 99, true);
	}

	public EXTGWTSTLVSpinner(EXTGWTSTLVSpinnerListener spinner, long value, long min, long max, int minStep, int maxStep) {
		this(spinner, value, min, max, minStep, maxStep, true);
	}
	
	public EXTGWTSTLVSpinner(EXTGWTSTLVSpinnerListener spinner, long value, long min, long max, int minStep, int maxStep, boolean constrained) {
		super();
		    
		spinnerListeners.add(spinner);
		this.value = value;
		this.constrained = constrained;
		this.step = minStep;
		this.minStep = minStep;
		this.maxStep = maxStep;
		this.min = min;
		this.max = max;
		this.initialSpeed = INITIAL_SPEED;
		
		// Handlers
		MouseUpHandler mouseUpHandler = new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				cancelTimer((Widget) event.getSource());
			}
		};
		incrementArrow.addMouseUpHandler(mouseUpHandler);
		decrementArrow.addMouseUpHandler(mouseUpHandler);
		
		MouseDownHandler mouseDownHandler = new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				Image sender = (Image) event.getSource();
				if (sender == incrementArrow) {
		        	//images.arrowUpPressed().applyTo(sender);
		        	increment = true;
		        	increase();
		        } else {
		        	//images.arrowDownPressed().applyTo(sender);
		        	increment = false;
		        	decrease();
		        }
				timer.scheduleRepeating(30);
			}
		};
		incrementArrow.addMouseDownHandler(mouseDownHandler);
		decrementArrow.addMouseDownHandler(mouseDownHandler);
		
		MouseOutHandler mouseOutHandler = new MouseOutHandler() {
			public void onMouseOut(MouseOutEvent event) {
				cancelTimer((Widget) event.getSource());
			}
		};
		incrementArrow.addMouseOutHandler(mouseOutHandler);
		decrementArrow.addMouseOutHandler(mouseOutHandler);
		
		MouseOverHandler mouseOverHandler = new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				Image sender = (Image) event.getSource();
				if (sender == incrementArrow) {
					//images.arrowUpHover().applyTo(sender);
				} else {
					//images.arrowDownHover().applyTo(sender);
				}
			}
		};
		incrementArrow.addMouseOverHandler(mouseOverHandler);
		decrementArrow.addMouseOverHandler(mouseOverHandler);
		
		//images.arrowUp().applyTo(incrementArrow);
		//images.arrowDown().applyTo(decrementArrow);
		
		fireOnValueChanged(true);
	}

	public void addSpinnerListener(EXTGWTSTLVSpinnerListener listener) {
		spinnerListeners.add(listener);
	}
	public void removeSpinnerListener(EXTGWTSTLVSpinnerListener listener) {
		spinnerListeners.remove(listener);
	}

	public Image getDecrementArrow() {
		return decrementArrow;
	}
	public Image getIncrementArrow() {
		return incrementArrow;
	}
	
	public long getMax() {
		return max;
	}
	public void setMax(long max) {
		this.max = max;
	}

	public long getMin() {
		return min;
	}
	public void setMin(long min) {
		this.min = min;
	}
	
	public int getMaxStep() {
		return maxStep;
	}
	public void setMaxStep(int maxStep) {
		this.maxStep = maxStep;
	}
	 
	public int getMinStep() {
		return minStep;
	}
	public void setMinStep(int minStep) {
		this.minStep = minStep;
	}

	public long getValue() {
		return value;
	}
	public void setValue(long value, boolean fireEvent) {
		this.value = value;
		if (fireEvent) {
			fireOnValueChanged(true);
		}
	}
	
	public boolean isConstrained() {
		return constrained;
	}
	
	public void setInitialSpeed(int initialSpeed) {
		this.initialSpeed = initialSpeed;
	}
	
	protected void decrease() {
		value -= step;
		if (constrained && value < min) {
			value = min;
			timer.cancel();
		}
		fireOnValueChanged(false);
	}
	  
	protected void increase() {
		value += step;
		if (constrained && value > max) {
			value = max;
			timer.cancel();
		}
		fireOnValueChanged(true);
	}
	 
	private void fireOnValueChanged(boolean increase) {
		for (EXTGWTSTLVSpinnerListener listener : spinnerListeners) {
			if (increase) {
				listener.onSpinning(value, step);
			} else {
				listener.onSpinning(value, -step);
			}
		}
	}
	
	private void cancelTimer(Widget sender) {
		step = minStep;
		if (sender == incrementArrow) {
	      // images.arrowUp().applyTo((Image) sender);
		} else {
			//images.arrowDown().applyTo((Image) sender);
	    }
		timer.cancel();
	}
}
