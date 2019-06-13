package com.flowingcode.addons.applayout;

import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.HasOrderedComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("fc-submenu")
@HtmlImport("bower_components/fc-applayout/fc-submenu.html")
@SuppressWarnings("serial")
public class SubMenuComponent extends MenuItemComponent implements HasOrderedComponents<SubMenuComponent> {

	public SubMenuComponent(MenuItem menuItem) {
		super(menuItem);
	}
	
}
