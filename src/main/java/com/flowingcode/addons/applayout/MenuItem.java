/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 - 2020 Flowing Code
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
import com.vaadin.flow.component.HasOrderedComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.icon.IconFactory;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.Command;
import java.util.function.Consumer;

/**
 * Menu item component.
 *
 * @author mlopez
 */
@SuppressWarnings("serial")
@NpmPackage(value = "@polymer/iron-collapse", version = "^3.0.1")
@NpmPackage(value = "@polymer/paper-item", version = "3.0.1")
@JsModule("./iron-collapse-button/iron-collapse-button.js")
@JsModule("./fc-applayout/fc-menuitem.js")
@Tag("fc-menuitem")
public class MenuItem extends SlottedMenuItem
    implements HasOrderedComponents<MenuItem>,
        HasMenuItemCommands<MenuItem>,
        HasMenuItemIcon<MenuItem> {

  static final String BLANK = "fc-menuitem-icons:empty";

  /** No argument constructor */
  public MenuItem() {}

  /** Create a new instance of {@code MenuItem} with a label. */
  public MenuItem(String label) {
    this.setLabel(label);
  }

  /** Create a new instance of {@code MenuItem} with a label and left-button command. */
  public MenuItem(String label, Command command) {
    this.setLabel(label);
    this.setCommand(command);
  }

  /** Create a new instance of {@code MenuItem} with a label for the given navigation target. */
  public MenuItem(String label, Class<? extends Component> navigationTarget) {
    setLink(new RouterLink(label, navigationTarget));
  }

  /** Create a new instance of {@code MenuItem} with a label and an icon. */
  public MenuItem(String label, String icon) {
    this(label);
    setIcon(icon);
  }

  /** Create a new instance of {@code MenuItem} with a label and an {@code IconFactory}. */
  public MenuItem(String label, IconFactory icon) {
    this(label);
    setIcon(icon.create().getElement().getAttribute("icon"));
  }

  /**
   * Create a new instance of {@code MenuItem} with a label, an {@code IconFactory}, and left-button
   * command.
   */
  public MenuItem(String label, IconFactory icon, Command command) {
    this(label, icon);
    this.setCommand(command);
  }

  /** Create a new instance of {@code MenuItem} with a label, an icon, and left-button command. */
  public MenuItem(String label, String icon, Command command) {
    this(label, icon);
    this.setCommand(command);
  }

  /** Adds the given menu items as children of this component. */
  public final MenuItem add(MenuItem... items) {
    this.add((Component[]) items);
    return this;
  }

  @Override
  public void add(Component... components) {
    HasOrderedComponents.super.add(components);
  }

  public String getTitle() {
    return getElement().getAttribute("title");
  }

  public MenuItem setTitle(String title) {
    getElement().setAttribute("title", title);
    return this;
  }

  public String getLabel() {
    return getElement().getAttribute("label");
  }

  public MenuItem setLabel(String label) {
    getElement().setAttribute("label", label);
    return this;
  }

  public MenuItem setLink(RouterLink link) {
    setLabel(link.getText());
    setHref(link.getHref());
    return this;
  }

  public MenuItem setHref(String href) {
    getElement().setProperty("href", href);
    return this;
  }

  public MenuItem configure(Consumer<MenuItem> consumer) {
    consumer.accept(this);
    return this;
  }

  /** @deprecated This method ignores the parameter. Use {@link #setIconBlank()}. */
  @Deprecated
  public void setIconSpacing(boolean value) {
    setIcon(MenuItem.BLANK);
  }

  public void setIconBlank() {
    setIcon(MenuItem.BLANK);
  }
}
