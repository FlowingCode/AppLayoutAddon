package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.dom.Element;

@JsModule("./fc-applayout/fc-separator.js")
@Tag("fc-separator")
public class MenuSeparator extends Component {

	private static final long serialVersionUID = 1L;

	public MenuSeparator() {}
	
	public MenuSeparator(String label) {
		Element div = new Element("div");
		div.setText(label);
		div.setAttribute("slot", "label");
		getElement().removeAllChildren();
		getElement().appendChild(div);
	}
	
	
}
