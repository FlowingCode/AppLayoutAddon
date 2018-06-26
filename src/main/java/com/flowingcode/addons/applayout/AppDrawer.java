package com.flowingcode.addons.applayout;

import java.util.ArrayList;
import java.util.List;

import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H4;

/**
 * Component that renders the app drawer
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@Tag("app-drawer")
@HtmlImport("bower_components/app-layout/app-drawer/app-drawer.html")
public class AppDrawer extends Component implements HasComponents {

    public AppDrawer(String title) {
    	getElement().setAttribute("id", "drawer");
    	getElement().setAttribute("swipe-open", true);
    	H4 htitle = new H4(title);
    	htitle.getElement().setAttribute("style", "text-align:center");
    	this.add(htitle);
    }
    
    public void setMenuItems(List<MenuItem> menuItems) {
    	List<Component> components = createComponents(menuItems);
    	PaperListbox pm = new PaperListbox(components);
    	add(pm);
    }

	private List<Component> createComponents(List<MenuItem> menuItems) {
		List<Component> components = new ArrayList<>();
    	for (MenuItem menuItem : menuItems) {
    		// TODO: Support for sub-menus
//    		if (menuItem.isSubMenuFolder()) {
//    			components.add(collectMenus(menuItem));
//    		} else {
    			PaperItem pi = new PaperItem(menuItem.getLabel(),menuItem.getCommand(), this);
    			components.add(pi);
//    		}
		}
		return components;
	}

//	private PaperListbox collectMenus(MenuItem topMenuItem) {
//		List<MenuItem> menuItems = topMenuItem.getSubMenuItems();
//    	List<Component> components = createComponents(menuItems);
//		return new PaperListbox(topMenuItem.getLabel(), components);
//	}
    
}
