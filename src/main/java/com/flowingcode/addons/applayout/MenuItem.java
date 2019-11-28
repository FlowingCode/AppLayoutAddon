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



import java.util.EnumMap;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.flowingcode.addons.applayout.MouseClickEvent.MouseButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.HasOrderedComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.shared.Registration;

/**
 * Menu item component.
 * 
 * @author mlopez
 *
 */
@Tag("fc-menuitem")
@HtmlImport("bower_components/fc-applayout/fc-menuitem.html")
@SuppressWarnings("serial")
public class MenuItem extends Component implements HasEnabled, HasOrderedComponents<MenuItem> {

	private EnumMap<MouseButton, Pair<Command, Registration>> commands;
	
	/** No argument constructor */
	public MenuItem() {}
	
	/** Create a new instance of {@code MenuItem} with a labelicon. */
	public MenuItem(String label) {
		this.setLabel(label);
	}
	
	/** Create a new instance of {@code MenuItem} with a label and left-button command. */
	public MenuItem(String label, Command command) {
		this.setLabel(label);
		this.setCommand(command);
	}
	
	/** Create a new instance of {@code MenuItem} with a label, a {@code VaadinIcon}, and left-button command. */
	public MenuItem(String label, VaadinIcon icon, Command command) {
		this(label, command);
		setIcon(icon.create().getElement().getAttribute("icon"));
	}
	
	/** Create a new instance of {@code MenuItem} with a label, an icon, and left-button command. */
	public MenuItem(String label, String icon, Command command) {
		this(label, command);
		setIcon(icon);
	}

	@Override
	public void add(Component... components) {
		HasOrderedComponents.super.add(components);
	}

	/**Adds the given menu items as children of this component.*/
	public final MenuItem add(MenuItem... items) {
		this.add((Component[])items);
		return this;
	}
	
	public String getLabel() {
		return getElement().getAttribute("label");
	}
	
	public MenuItem setLabel(String label) {
		getElement().setAttribute("label", label);
		return this;
	}
	
	public String getIcon() {
		return getElement().getAttribute("icon");
	}
	
	public MenuItem setIcon(String icon) {
		if (icon!=null) {
			getElement().setAttribute("icon", icon);
			getElement().removeAttribute("image");
		} else {
			getElement().removeAttribute("icon");
		}
		return this;
	}
	
	public String getImage() {
		return getElement().getAttribute("image");
	}
	
	public MenuItem setImage(String imageUrl) {
		if (imageUrl!=null) {
			getElement().setAttribute("image", imageUrl);
			getElement().removeAttribute("icon");
		} else {
			getElement().removeAttribute("image");
		}
		return this;
	}
	
	public MenuItem setCommand(Command command) {
		setCommand(MouseButton.LEFT, command);
		return this;
	}
	
	public MenuItem setCommand(MouseButton button, Command command) {
		if (command != null || commands != null && commands.containsKey(button)) {
			Optional.ofNullable(commands).orElseGet(()->commands=new EnumMap<>(MouseButton.class)).compute(button, (_button, commandAndRegistration)->{
				Optional.ofNullable(commandAndRegistration).map(Pair::getRight).ifPresent(Registration::remove);
				return command!=null ? Pair.of(command, addMouseClickEvent(ev->{if (ev.getButton()==button) command.execute();})) : null;
			});
		}
		return this;
	}
	
	private Registration addMouseClickEvent(MouseClickListener<MenuItem> listener) {
		return getElement().addEventListener("item-click", ev->{
			int btnIndex = (int) ev.getEventData().getNumber("event.detail");
			MouseButton button = MouseButton.values()[btnIndex];
			listener.onComponentEvent(new MouseClickEvent<MenuItem>(this, button, true));
		}).addEventData("event.detail");
	}

	public Command getCommand() {
		return getCommand(MouseButton.LEFT);		
	}
	
	public Command getCommand(MouseButton button) {
		return Optional.ofNullable(commands).map(m->m.get(button)).map(Pair::getLeft).orElse(null);
	}

}

