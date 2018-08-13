package com.flowingcode.addons.applayout;

import java.util.ArrayList;

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

import java.util.Arrays;

import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

/**
 * Component that renders the div that contains the entire layout.
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/font-roboto/roboto.html")
@HtmlImport("bower_components/iron-icons/iron-icons.html")
@HtmlImport("bower_components/app-layout/app-scroll-effects/app-scroll-effects.html")
@HtmlImport("bower_components/app-layout/app-toolbar/app-toolbar.html")
@HtmlImport("styles/shared-styles.html")
public class AppLayout extends Div implements PageConfigurator {

	AppDrawer drawer;
	AppHeader header;
	
	public AppLayout(String title) {
		drawer = new AppDrawer(title);
		header = new AppHeader(title, drawer);
        add(header);
        add(drawer);
        setWidth("100%");
        setHeight("64px");
	}
	
	public void setMenuItems(MenuItem... menuitems) {
		drawer.setMenuItems(Arrays.asList(menuitems));
	}
	
	public void clearMenuItems() {
		drawer.setMenuItems(new ArrayList<MenuItem>());
	}
	
	public void setToolbarIconButtons(MenuItem... menuItems) {
		header.setToolbarIconButtons(menuItems);
	}
	
	public void setMenuVisible(boolean visible) {
		drawer.setVisible(visible);
		header.setMenuIconVisible(visible);
	}
	
	public boolean isMenuVisible() {
		return drawer.isVisible();
	}

	@Override
	public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("viewport", "width=device-width, initial-scale=1.0");
	}
	
}
