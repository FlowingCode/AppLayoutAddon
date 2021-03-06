/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 - 2021 Flowing Code
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
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Component that renders the div that contains the entire layout.
 *
 * @author mlopez
 */
@SuppressWarnings("serial")
@Tag("fc-applayout")
@JsModule("@flowingcode/fc-applayout/fc-applayout.js")
@NpmPackage(value = "@flowingcode/fc-applayout", version = "0.9.3")
@CssImport(value = "./styles/applayout-styles.css", themeFor = "fc-applayout")
public class AppLayout extends Div implements PageConfigurator {

  private final List<Component> menuItems = new ArrayList<>();
  private final List<Component> toolbarComponents = new ArrayList<>();

  public AppLayout(String title) {
    this(null, title, null);
  }

  public AppLayout(Component menuHeader, String title) {
    this(menuHeader, title, null);
  }

  public AppLayout(Image logo, Component menuHeader, String title) {
    this(menuHeader, title, logo);
  }

  private AppLayout(Component menuHeader, String aTitle, Image aLogo) {
    if (aLogo != null) {
      aLogo.getElement().setAttribute("slot", "title");
      add(aLogo);
    }
    if (menuHeader != null) {
      menuHeader.getElement().setAttribute("slot", "profile");
      add(menuHeader);
    }
    Div title = new Div();
    title.setText(aTitle);
    title.getElement().setAttribute("slot", "title");
    add(title);
  }

  public void setMenuItems(Component... someMenuitems) {
    this.menuItems.addAll(Arrays.asList(someMenuitems));
    this.menuItems.forEach(item -> item.getElement().setAttribute("slot", "menu"));
    this.add(someMenuitems);
  }

  public void clearMenuItems() {
    this.getChildren()
        .forEach(
            item -> {
              if (this.menuItems.contains(item)) this.remove(item);
            });
    this.menuItems.clear();
  }

  public void setToolbarIconButtons(Component... components) {
    toolbarComponents.forEach(this::remove);
    addToolbarIconButtons(components);
  }

  public void addToolbarIconButtons(Component... components) {
    List<Component> componentsToAdd = Arrays.asList(components);
    componentsToAdd.forEach(comp -> comp.getElement().setAttribute("slot", "toolbar"));
    toolbarComponents.addAll(componentsToAdd);
    this.add(components);
  }

  public void addToolbarIconButtonAsFirst(Component component) {
    toolbarComponents.add(0, component);
    toolbarComponents.forEach(this::remove);
    addToolbarIconButtons(toolbarComponents.toArray(new Component[toolbarComponents.size()]));
  }

  public void clearToolbarIconButtons() {
    toolbarComponents.forEach(this::remove);
    toolbarComponents.clear();
  }

  public void setMenuVisible(boolean visible) {
    this.getElement().setProperty("drawer-visible", visible);
  }

  public boolean isMenuVisible() {
    return this.getElement().getProperty("drawer-visible", true);
  }

  /** Set the toolbar title */
  public void setCaption(String caption) {
    this.getElement().setAttribute("title", caption);
  }

  @Override
  public void configurePage(InitialPageSettings settings) {
    settings.addMetaTag("viewport", "width=device-width, initial-scale=1.0");
  }

  /** Mantains the header fixed at the top so it never moves away. */
  public void setFixed(boolean fixed) {
    this.getElement().setAttribute("fixed", fixed);
  }

  /** Slides back the header when scrolling back up. */
  public void setReveals(boolean reveals) {
    this.getElement().setAttribute("reveals", reveals);
  }

  /** Create an area at the edge of the screen to swipe open the app-drawer */
  public void setSwipeOpen(boolean swipeOpen) {
    this.getElement().setAttribute("swipeOpen", swipeOpen);
  }
}
