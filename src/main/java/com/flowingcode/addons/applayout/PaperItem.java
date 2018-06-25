package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.server.Command;

/**
 * Component that renders a paper-item
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/paper-item/paper-item.html")
@Tag("paper-item")
public class PaperItem extends Component implements HasText {

	public PaperItem(String title, Command command, AppDrawer appDrawer) {
		setText(title);
		this.getElement().addEventListener("click", e->{
			command.execute();
			if (appDrawer!=null)
				UI.getCurrent().getPage().executeJavaScript("" + appDrawer.getId().get() + ".toggle()");
		});
	}

	public PaperItem(String label, Command command) {
		this(label, command, null);
	}

}
