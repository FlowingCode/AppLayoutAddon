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



import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.server.Command;

/**
 * Toolbar menu item component.
 * 
 */
@SuppressWarnings("serial")
//@NpmPackage(value="@polymer/iron-icon", version = "^3.0.1")
@NpmPackage(value="@polymer/iron-collapse", version = "^3.0.1")
@JsModule("./iron-collapse-button/iron-collapse-button.js")
@JsModule("./fc-applayout/fc-menuitem.js")
@Tag("paper-icon-button")
public class ToolbarIconButton extends SlottedMenuItem implements HasMenuItemCommands<ToolbarIconButton>, HasMenuItemIcon<ToolbarIconButton> {

	/** No argument constructor */
	public ToolbarIconButton() {}
		
	/** Create a new instance of {@code MenuItem} with a title. */
	public ToolbarIconButton(String title) {
		this.setTitle(title);
	}

	/** Create a new instance of {@code MenuItem} with a title and left-button command. */
	public ToolbarIconButton(String title, Command command) {
		this.setTitle(title);
		this.setCommand(command);
	}

	/** Create a new instance of {@code MenuItem} with a title, a {@code VaadinIcon}, and left-button command. */
	public ToolbarIconButton(String title, VaadinIcon icon, Command command) {
		this(title, command);
		setIcon(icon.create().getElement().getAttribute("icon"));
	}
	
	/** Create a new instance of {@code MenuItem} with a title, an icon, and left-button command. */
	public ToolbarIconButton(String title, String icon, Command command) {
		this(title, command);
		setIcon(icon);
	}

	public String getTitle() {
		return getElement().getAttribute("title");
	}
	
	public ToolbarIconButton setTitle(String title) {
		getElement().setAttribute("title", title);
		return this;
	}
	
}

