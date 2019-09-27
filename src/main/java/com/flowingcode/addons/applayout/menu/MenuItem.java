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
package com.flowingcode.addons.applayout.menu;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.vaadin.flow.component.icon.VaadinIcon;
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
	private String image;
	private Command command;
	private List<MenuItem> subMenuItems = new ArrayList<>();
	private Runnable refreshCallback;
	private boolean enabled = true;
	
	
	/** No argument constructor */
	public MenuItem() {}
	
	/** Create a new instance of {@code MenuItem} with a label. */
	public MenuItem(String label) {
		this.setLabel(label);
	}

	/** @deprecated (for removal) Use fluent API. */
	@Deprecated
	public MenuItem(String label, MenuItem... subMenuItems) {
		this.label = label;
		this.subMenuItems = Arrays.asList(subMenuItems);
	}
	
	/** @deprecated (for removal) Use fluent API. */
	@Deprecated
	public MenuItem(String label, String icon, MenuItem... subMenuItems) {
		this.label = label;
		this.icon = icon;
		this.subMenuItems = Arrays.asList(subMenuItems);
	}

	/** @deprecated (for removal) Use fluent API */
	@Deprecated
	public MenuItem(String label, URL image, MenuItem... subMenuItems) {
		this.label = label;
		this.setImageURL(image);
		this.subMenuItems = Arrays.asList(subMenuItems);
	}

	/** Create a new instance of {@code MenuItem} with a label and left-button command. */
	public MenuItem(String label, Command command) {
		this.setLabel(label);
		this.setCommand(command);
	}

	/** Create a new instance of {@code MenuItem} with a label, an icon, and left-button command. */
	public MenuItem(String label, String icon, Command command) {
		setLabel(label);
		setCommand(command);
		setIcon(icon);
	}

	public MenuItem(String label, URL image, Command command) {
		this.label = label;
		this.command = command;
		this.setImageURL(image);
	}
	
	/** Create a new instance of {@code MenuItem} with a label, a {@code VaadinIcon}, and left-button command. */
	public MenuItem(String label, VaadinIcon icon, Command command) {
		this(label, getIconName(icon), command);
	}
	
	/**Adds the given menu items as children of this component.*/
	public MenuItem add(MenuItem... items) {
		Stream.of(items).forEach(Optional.ofNullable(subMenuItems).orElseGet(()->subMenuItems = new ArrayList<>())::add);
		return this;
	}
	
	private static String getIconName(VaadinIcon icon) {
		return icon.create().getElement().getAttribute("icon");
	}

	public String getLabel() {
		return label;
	}

	public MenuItem setLabel(String label) {
		this.label = label;
		if (refreshCallback!=null) {
			this.refreshCallback.run();
		}		
		return this;
	}

	public Command getCommand() {
		return command;
	}

	public MenuItem setCommand(Command command) {
		this.command = command;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public MenuItem setIcon(String icon) {
		this.icon = icon;
		if (refreshCallback!=null) {
			this.refreshCallback.run();
		}
		return this;
	}

	public List<MenuItem> getSubMenuItems() {
		return subMenuItems;
	}

	public void setSubMenuItems(List<MenuItem> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}

	/**
	 * @return true if this item has sub menu items
	 * @deprecated (for removal)
	 */
	@Deprecated
	public boolean isSubMenuFolder() {
		return !getSubMenuItems().isEmpty();
	}

	/**
	 * This allows you to configure a callback that is called whenever you change the label and icon
	 * @param refreshCallback
	 */
	public void setRefreshCallback(Runnable refreshCallback) {
		this.refreshCallback = refreshCallback;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImage() {
		return image;
	}
	
	public MenuItem setImage(String image) {
		this.image = image;
		return this;
	}
	
	/** @deprecated (for removal) Use {@link #getImage}*/
	@Deprecated
	public URL getImageURL() {
		if (this.image!=null) {
			try {
				return new URL(image);
			} catch (MalformedURLException e) {
				throw new IllegalStateException();
			}
		} else {
			return null;
		}
	}

	/** @deprecated (for removal) Use {@link #setImage(String)}*/
	@Deprecated
	public void setImageURL(URL imageURL) {
		this.image = Optional.ofNullable(imageURL).map(URL::toExternalForm).orElse(null);
	}

}
