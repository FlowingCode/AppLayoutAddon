package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;

/**
 * @author mlope
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/iron-collapse-button/iron-collapse-button.html")
@Tag("iron-collapse-button")
public class CollapseButton extends Component implements HasComponents {
	
	public CollapseButton(String label, Component...items) {
		PaperItem pi = new PaperItem(label);
		pi.getElement().setAttribute("slot", "collapse-trigger");
		this.add(pi);
		Div div = new Div();
		div.getElement().setAttribute("slot", "collapse-content");
		div.add(items);
		this.add(div);
	}

}
