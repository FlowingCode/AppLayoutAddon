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

import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Image;

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
    	this(null,title,drawer);
    }

    public AppHeader(Image logo, String title, AppDrawer drawer) {
    	this.getElement().setAttribute("reveals", true);
    	this.getElement().setAttribute("condenses", true);
    	this.getElement().setAttribute("effects", true);
    	appToolbar = new AppToolbar(logo, title, drawer);
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

	public void setMenuIconVisible(boolean visible) {
		appToolbar.setMenuIconVisible(visible);
	}

	void setHeight(String height) {
		appToolbar.getElement().getStyle().set("height", height);
	}

}
