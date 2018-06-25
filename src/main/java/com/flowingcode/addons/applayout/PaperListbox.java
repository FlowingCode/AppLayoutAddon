package com.flowingcode.addons.applayout;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

/**
 * Component that renders a paper-listbox
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/paper-listbox/paper-listbox.html")
@Tag("paper-listbox")
public class PaperListbox extends Component implements HasComponents {
	
	public PaperListbox(List<Component> components) {
		components.stream().forEach(c->this.add(c));
	}

}
