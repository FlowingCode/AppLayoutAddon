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

import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.icon.IconFactory;
import com.vaadin.flow.server.Command;

/** Toolbar menu item component. */
@SuppressWarnings("serial")
@NpmPackage(value = "@polymer/paper-icon-button", version = "3.0.2")
@JsModule("@polymer/paper-icon-button/paper-icon-button.js")
@Tag("paper-icon-button")
public class ToolbarIconButton extends SlottedMenuItem
    implements HasEnabled,
        HasMenuItemCommands<ToolbarIconButton>,
        HasMenuItemIcon<ToolbarIconButton> {

  /** No argument constructor */
  public ToolbarIconButton() {}

  /** Create a new instance of {@code ToolbarIconButton} with a title */
  public ToolbarIconButton(String title) {
    this.setTitle(title);
  }

  /** Create a new instance of {@code ToolbarIconButton} with a left-button command. */
  public ToolbarIconButton(Command command) {
    this.setCommand(command);
  }

  /** Create a new instance of {@code MenuItem} with a title and a left-button command. */
  public ToolbarIconButton(String title, Command command) {
    this.setTitle(title);
    this.setCommand(command);
  }

  /**
   * Create a new instance of {@code ToolbarIconButton} with a title, an {@code IconFactory}, and
   * left-button command.
   */
  public ToolbarIconButton(String title, IconFactory icon, Command command) {
    this(command);
    setTitle(title);
    setIcon(icon.create().getElement().getAttribute("icon"));
  }

  /** Create a new instance of {@code MenuItem} with a title, an icon, and left-button command. */
  public ToolbarIconButton(String title, String icon, Command command) {
    this(command);
    setTitle(title);
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
