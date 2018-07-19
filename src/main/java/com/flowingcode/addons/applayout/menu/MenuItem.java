package com.flowingcode.addons.applayout.menu;

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

import com.vaadin.flow.server.Command;

/**
 * Model object for representing menu and submenu items
 * 
 * @author mlopez
 *
 */
public class MenuItem {

	private String label;
	private String icon;
	private Command command;
	private List<MenuItem> subMenuItems = new ArrayList<>();
	private Runnable refreshCallback;

	public MenuItem(String label, MenuItem... subMenuItems) {
		this.label = label;
		this.subMenuItems = Arrays.asList(subMenuItems);
	}

	public MenuItem(String label, String icon, MenuItem... subMenuItems) {
		this.label = label;
		this.icon = icon;
		this.subMenuItems = Arrays.asList(subMenuItems);
	}

	public MenuItem(String label, Command command) {
		this.label = label;
		this.command = command;
	}

	public MenuItem(String label, String icon, Command command) {
		this.label = label;
		this.command = command;
		this.icon = icon;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
		this.refreshCallback.run();
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
		this.refreshCallback.run();
	}

	public List<MenuItem> getSubMenuItems() {
		return subMenuItems;
	}

	public void setSubMenuItems(List<MenuItem> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}

	/**
	 * @return true if this item has sub menu items
	 */
	public boolean isSubMenuFolder() {
		return getSubMenuItems().size() > 0;
	}

	/**
	 * This allows you to configure a callback that is called whenever you change the label and icon
	 * @param refreshCallback
	 */
	public void setRefreshCallback(Runnable refreshCallback) {
		this.refreshCallback = refreshCallback;
	}


}
