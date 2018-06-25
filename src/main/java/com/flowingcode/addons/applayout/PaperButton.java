package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.server.Command;

/**
 * Component that renders a paper-button
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@Tag("paper-button")
@HtmlImport("bower_components/paper-button/paper-button.html")
public class PaperButton extends Component {
	
    public PaperButton(String label) {
    	setLabel(label);
    }

    public PaperButton(String label, Command command) {
    	this.setLabel(label);
		this.getElement().addEventListener("click", e->{
			command.execute();
		});
    }

    public void setLabel(String label) {
    	this.getElement().setText(label);
    }
    
    
}
