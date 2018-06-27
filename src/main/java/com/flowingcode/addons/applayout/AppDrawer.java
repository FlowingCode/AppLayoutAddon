package com.flowingcode.addons.applayout;

/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 Flowing Code
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
