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

import java.util.stream.Stream;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasOrderedComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.dom.Element;

/**
 * Component that renders the app toolbar
 *
 * @author mlopez
 */
@SuppressWarnings("serial")
@NpmPackage(value = "@polymer/app-layout", version = AppLayout.NPM_VERSION)
@NpmPackage(value = "@polymer/iron-icons", version = "^3.0.0")
@JsModule("@polymer/app-layout/app-toolbar/app-toolbar.js")
@JsModule("@polymer/iron-icons/iron-icons.js")
@Tag("app-toolbar")
public class AppToolbar extends Component {

  private ToolbarIconButton menu;
  private Div divTitle;
  private int index;

	private HasOrderedComponents hasOrderedComponents = new HasOrderedComponents() {
		@Override
		public Element getElement() {
			return AppToolbar.this.getElement();
		}

		@Override
		public Stream<Component> getChildren() {
			return ((Component) AppToolbar.this).getChildren();
		}

	};
  public AppToolbar(String title, AppDrawer drawer) {
    this(null, title, drawer);
  }

  public AppToolbar(Image logo, String title, AppDrawer drawer) {
    menu = new ToolbarIconButton().setIcon("menu");
    add(menu);

    drawer.getId().ifPresent(id -> menu.getElement().setAttribute("onclick", id + ".toggle()"));
    if (logo != null) {
      add(logo);
    }

    divTitle = new Div();
    divTitle.getElement().setAttribute("main-title", true);
    setTitle(title);
    add(divTitle);

    index = hasOrderedComponents.getComponentCount();
  }

  private void add(Component... components) {
    hasOrderedComponents.add(components);
  }

  private void addComponentAtIndex(int index, Component component) {
    hasOrderedComponents.addComponentAtIndex(index, component);
  }

  public void setTitle(String title) {
    divTitle.setText(title);
  }

  public void clearToolbarIconButtons() {
    while (hasOrderedComponents.getComponentCount() > index) {
      hasOrderedComponents.remove(hasOrderedComponents.getComponentAt(index));
    }
  }

  public void setMenuIconVisible(boolean visible) {
    menu.setVisible(visible);
  }

  public void addToolbarIconButtons(Component... components) {
    this.add(components);
  }

  public void addToolbarIconButtonAsFirst(Component component) {
    this.addComponentAtIndex(index, component);
  }
}
