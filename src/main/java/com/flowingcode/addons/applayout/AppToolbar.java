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



import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

/**
 * Component that renders the app toolbar
 *
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/app-layout/app-toolbar/app-toolbar.html")
@HtmlImport("bower_components/iron-icons/iron-icons.html")
@NpmPackage(value = "@polymer/app-layout", version= AppLayout.NPM_VERSION)
@NpmPackage(value = "@polymer/iron-icons", version = "^3.0.0")
@JsModule("@polymer/app-layout/app-toolbar/app-toolbar.js")
@JsModule("@polymer/iron-icons/iron-icons.js")
@Tag("app-toolbar")
public class AppToolbar extends Component implements HasComponents {

	private ToolbarIconButton menu;
	private Component ctitle;
	private Div divTitle;

    public AppToolbar(String title, AppDrawer drawer) {
    	this(null,title, drawer);
    }

    public AppToolbar(Image logo, String title, AppDrawer drawer) {
    	menu = new ToolbarIconButton().setIcon("menu");    	
    	drawer.getId().ifPresent(id -> menu.getElement().setAttribute("onclick", id + ".toggle()"));
    	this.add(menu);
    	if (logo!=null) {
    		ctitle = logo;
    		this.add(ctitle);
    	}
    	divTitle = new Div();
    	divTitle.getElement().setAttribute("main-title", true);
    	this.add(divTitle);
    	setTitle(title);
    }

    public void setTitle(String title) {
    	divTitle.setText(title);
    }

	public void setToolbarIconButtons(Component... components) {
		this.removeAll();
		this.add(menu);
		if (ctitle!=null) this.add(ctitle);
		if (divTitle!=null) this.add(divTitle);
		this.add(components);
	}
	
	public void setMenuIconVisible(boolean visible) {
		menu.setVisible(visible);
	}


}
