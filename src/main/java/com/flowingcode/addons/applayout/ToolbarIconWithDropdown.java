package com.flowingcode.addons.applayout;

import com.awesomecontrols.quickpopup.QuickPopup;
import com.awesomecontrols.quickpopup.QuickPopup.Align;
import com.vaadin.flow.component.html.Div;

public class ToolbarIconWithDropdown extends ToolbarIconButton {

	private QuickPopup qp;
	
	public ToolbarIconWithDropdown() {
		setIcon("settings");
				
		Div vl = new Div();
		//vl.setPadding(false);
		vl.add(new MenuItem("Hello").add(new MenuItem("World").setCommand(()->{
			close();
		})));
		
		getElement().appendChild(vl.getElement());
		qp = new QuickPopup(this.getElement(), vl);
		qp.setAlign(Align.BOTTOM_LEFT);		
		setCommand(()->{
			if (qp.isVisible()) {
				qp.hide();
			} else {
				qp.show();
			}
		});
	}
	
	public void close() {
		qp.hide();		
	}
	
}
