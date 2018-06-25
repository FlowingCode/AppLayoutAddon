package com.flowingcode.addons.applayout;

import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

/**
 * Component that renders the app header
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@Tag("app-header")
@HtmlImport("bower_components/app-layout/app-header/app-header.html")
public class AppHeader extends Component implements HasComponents {

	private AppToolbar appToolbar;
	
    public AppHeader(String title, AppDrawer drawer) {
    	this.getElement().setAttribute("reveals", true);
    	this.getElement().setAttribute("condenses", true);
    	this.getElement().setAttribute("effects", true);
    	appToolbar = new AppToolbar(title, drawer);
    	this.add(appToolbar);
    }
    
    public void setAppToolbar(AppToolbar appToolbar) {
    	this.appToolbar = appToolbar;
    	this.add(appToolbar);
    }
    
    public AppToolbar getAppToolbar() {
    	return appToolbar;
    }

	public void setToolbarIconButtons(MenuItem[] menuItems) {
		appToolbar.setToolbarIconButtons(menuItems);
	}
    
}
