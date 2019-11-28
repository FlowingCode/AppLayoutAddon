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
import java.util.Arrays;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
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
@HtmlImport("bower_components/app-layout/app-scroll-effects/app-scroll-effects.html")
@HtmlImport("frontend://styles/applayout-styles.html")
@NpmPackage(value = "@polymer/app-layout", version=AppLayout.NPM_VERSION)
@NpmPackage(value = "@polymer/font-roboto", version="3")
@JsModule("@polymer/app-layout/app-drawer/app-drawer.js")
@JsModule("@polymer/app-layout/app-scroll-effects/app-scroll-effects.js")
@JsModule("@polymer/font-roboto/roboto.js")
@CssImport("./styles/applayout-styles.css")
public class AppLayout extends Div implements PageConfigurator {

	static final String NPM_VERSION = "3.0.2";
	
	private AppDrawer drawer;
	private AppHeader header;
	
	public AppLayout(String title) {
		drawer = new AppDrawer(title);
		configureAppLayout(title, null);
	}
	
	public AppLayout(Component menuHeader, String title) {
		drawer = new AppDrawer(menuHeader);
		configureAppLayout(title, null);
	}

	public AppLayout(Image logo, Component menuHeader, String title) {
		drawer = new AppDrawer(menuHeader);
		configureAppLayout(title, logo);
	}

	private void configureAppLayout(String title, Image logo) {
		header = new AppHeader(logo, title, drawer);
        add(header);
        add(drawer);
        setWidth("100%");
        setHeight("64px");
	}

	@Override
	public void setHeight(String height) {
		super.setHeight(height);
		header.setHeight(height);
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

	/**Set the toolbar title*/
	public void setCaption(String caption) {
		header.getAppToolbar().setTitle(caption);
	}

	@Override
	public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("viewport", "width=device-width, initial-scale=1.0");
	}

	/**Mantains the header fixed at the top so it never moves away.*/
	public void setFixed(boolean fixed) {
		header.setFixed(fixed);
	}

	/**Slides back the header when scrolling back up.*/
	public void setReveals(boolean reveals) {
		header.setReveals(reveals);
	}

	/**Create an area at the edge of the screen to swipe open the app-drawer*/
	public void setSwipeOpen(boolean swipeOpen) {
		drawer.setSwipeOpen(swipeOpen);
	}
	
}
