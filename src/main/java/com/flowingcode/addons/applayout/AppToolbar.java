package com.flowingcode.addons.applayout;

import java.util.ArrayList;
import java.util.List;

import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;

/**
 * Component that renders the app toolbar
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@Tag("app-toolbar")
@HtmlImport("bower_components/app-layout/app-toolbar/app-toolbar.html")
public class AppToolbar extends Component implements HasComponents {
	
	PaperIconButton menu;
	Div divTitle;
	
    public AppToolbar(String title, AppDrawer drawer) {
    	menu = new PaperIconButton("menu");
    	menu.getElement().setAttribute("onclick", "" + drawer.getId().get() + ".toggle()");
    	divTitle = new Div();
    	divTitle.setText(title);
    	divTitle.getElement().setAttribute("main-title", true);
    	this.add(menu,divTitle);
    }

	public void setToolbarIconButtons(MenuItem[] menuItems) {
		List<PaperIconButton> toolbarIconButtons = createToolbarIconButtons(menuItems);
		this.removeAll();
		this.add(menu,divTitle);
		toolbarIconButtons.forEach(tib->this.add(tib));
	}

	private List<PaperIconButton> createToolbarIconButtons(MenuItem[] menuItems) {
		List<PaperIconButton> result = new ArrayList<>();
		for (MenuItem menuItem : menuItems) {
			result.add(new PaperIconButton(menuItem.getIcon(),menuItem.getCommand()));
		}
		return result;
	}

    
}
