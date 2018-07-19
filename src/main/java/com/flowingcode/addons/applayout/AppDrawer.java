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
import java.util.Arrays;
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
    	Component[] components = createComponents(menuItems);
    	PaperListbox pm = new PaperListbox(Arrays.asList(components));
    	add(pm);
    }

	private Component[] createComponents(List<MenuItem> menuItems) {
		List<Component> components = new ArrayList<>();
    	for (MenuItem menuItem : menuItems) {
    		if (menuItem.isSubMenuFolder()) {
    			components.add(collectMenus(menuItem));
    		} else {
    			if (menuItem.getIcon()==null) {
    				PaperItem pi = new PaperItem(menuItem.getLabel(),menuItem.getCommand(), this);
    				components.add(pi);
    				menuItem.setRefreshCallback(()->pi.setText(menuItem.getLabel()));
    			} else {
    				PaperIconItem pi = new PaperIconItem(menuItem.getLabel(), menuItem.getIcon(),menuItem.getCommand(), this);
    				components.add(pi);
    				menuItem.setRefreshCallback(()->{
    					pi.setTitle(menuItem.getLabel());
    					pi.setIcon(menuItem.getIcon());
    				});
    			}
    		}
		}
		return components.toArray(new Component[] {});
	}

	private CollapseButton collectMenus(MenuItem topMenuItem) {
		List<MenuItem> menuItems = topMenuItem.getSubMenuItems();
    	Component[] components = createComponents(menuItems);
		return new CollapseButton(topMenuItem.getLabel(), topMenuItem.getIcon(), components);
	}
    
}
