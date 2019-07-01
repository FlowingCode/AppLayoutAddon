/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 - 2019 Flowing Code
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
package com.flowingcode.addons.applayout;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.shared.Registration;

/**
 * Component that renders the app drawer
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@Tag("app-drawer")
@HtmlImport("bower_components/app-layout/app-drawer/app-drawer.html")
@HtmlImport("bower_components/iron-scroll-target-behavior/iron-scroll-target-behavior.html")
public class AppDrawer extends Component implements HasComponents {
	
	private final PaperListbox pm = new PaperListbox(Collections.emptyList());
	private final Component header;

    public AppDrawer(String title) {
    	this(new H4(title));
    	header.getElement().setAttribute("style", "text-align:center");
    }
   
    public AppDrawer(Component headerComponent) {
    	this.header = headerComponent;
    	getElement().setAttribute("id", "drawer");
    	setSwipeOpen(true);
    	this.add(headerComponent);
    	this.add(pm);
    	
    	Registration[] r = new Registration[1];
    	r[0] = getElement().addEventListener("app-drawer-transitioned", ev->{
    		//need to adjust the height after the drawer has been rendered
    		getUI().ifPresent(ui->ui.getPage().executeJavaScript("$1.style.height='calc(100% - '+($0.scrollHeight+16)+'px)'", header, pm));
    		r[0].remove();
    	});
    	
    	getElement().getStyle().set("--fc-separator-background-color", "var(--app-drawer-content-container_-_background-color)");
    }
    	
    public void setSwipeOpen(boolean swipeOpen) {
    	getElement().setAttribute("swipe-open", swipeOpen);
	}

	public void setMenuItems(List<MenuItem> menuItems) {
    	Component[] components = createComponents(menuItems);
    	pm.removeAll();
    	pm.add(components);
    }

	private Component[] createComponents(List<MenuItem> menuItems) {
		List<Component> components = new ArrayList<>();
    	for (MenuItem menuItem : menuItems) {
    		MenuItemComponent mi;
    		if (!menuItem.getSubMenuItems().isEmpty()) {
    			List<MenuItem> submenuItems = menuItem.getSubMenuItems();
    			Component[] children = createComponents(submenuItems);
    	    	mi = new SubMenuComponent(menuItem);
    	    	((SubMenuComponent)mi).add(children);    	    	
    		} else {
    			mi = new MenuItemComponent(menuItem);
    			mi.addMouseClickEvent(ev->{    				
    				switch (ev.getButton()) {
						case LEFT:
							Optional.ofNullable(menuItem.getCommand()).ifPresent(Command::execute);
							close();
							break;
						case MIDDLE:
							Optional.ofNullable(menuItem.getMiddleButtonCommand()).ifPresent(Command::execute);
							close();
							break;
						case RIGHT:
							Optional.ofNullable(menuItem.getRightButtonCommand()).ifPresent(Command::execute);
							break;
    				}
    			});
    		}    		    		
    		
	    	components.add(mi);
	    	menuItem.setRefreshCallback(()->mi.configure(menuItem));
		}
    	
		return components.toArray(new Component[] {});
	}
	
	/**Close the app-drawer.*/
	public void close() {
		getUI().ifPresent(ui->ui.getPage().executeJavaScript("$0.close()", this));
	}
	
}
