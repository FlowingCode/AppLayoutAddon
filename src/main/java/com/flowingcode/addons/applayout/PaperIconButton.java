package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.server.Command;

/**
 * Component that renders a paper-icon-button
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@Tag("paper-icon-button")
@HtmlImport("bower_components/paper-icon-button/paper-icon-button.html")
public class PaperIconButton extends Component  {
	
    public PaperIconButton(String icon) {
    	this.setIcon(icon);
    }

    public PaperIconButton(String icon, Command command) {
    	this.setIcon(icon);
		this.getElement().addEventListener("click", e->{
			command.execute();
		});
    }

    public void setIcon(String icon) {
    	this.getElement().setAttribute("icon", icon);
    
    }
    
    
}
