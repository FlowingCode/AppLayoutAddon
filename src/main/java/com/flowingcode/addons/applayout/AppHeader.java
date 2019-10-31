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



import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Image;

/**
 * Component that renders the app header
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/app-layout/app-header/app-header.html")
@NpmPackage(value = "@polymer/app-layout", version= AppLayout.NPM_VERSION)
@JsModule("@polymer/app-layout/app-header/app-header.js")
@Tag("app-header")
public class AppHeader extends Component implements HasComponents {

	private AppToolbar appToolbar;
	
    public AppHeader(String title, AppDrawer drawer) {
    	this(null,title,drawer);
    }

    public AppHeader(Image logo, String title, AppDrawer drawer) {
    	setReveals(true);
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

	/**Mantains the header fixed at the top so it never moves away.*/
	public void setFixed(boolean fixed) {
		this.getElement().setAttribute("fixed", fixed);
	}

	/**Slides back the header when scrolling back up.*/
	public void setReveals(boolean reveals) {
		this.getElement().setAttribute("reveals", reveals);
	}

  void setHeight(String height) {
		appToolbar.getElement().getStyle().set("height", height);
	}

}
