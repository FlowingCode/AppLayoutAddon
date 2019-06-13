package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

public class MouseClickEvent<T extends Component> extends ComponentEvent<T> {

	private static final long serialVersionUID = 1L;

	public enum MouseButton {
        LEFT, MIDDLE, RIGHT;
    }

	private MouseButton button;
		
	public MouseClickEvent(T source, MouseButton button, boolean fromClient) {
		super(source, fromClient);
    	this.button = button;
	}
	
	public MouseButton getButton() {
		return button;
	}

}
