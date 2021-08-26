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
@NpmPackage(value = "@flowingcode/fc-applayout", version = "0.9.5")
@CssImport(value = "./styles/applayout-styles.css", themeFor = "fc-applayout")
public class AppLayout extends Div implements PageConfigurator {

  private static final String PROFILE_SLOT_NAME = "profile";
  private static final String APP_LAYOUT_TITLE_SLOT_NAME = "title";
  private static final String TITLE_ATTRIBUTE_NAME = "title";
  private final List<Component> menuItems = new ArrayList<>();
  private final List<Component> toolbarComponents = new ArrayList<>();

  public AppLayout() {
    this(null,"",null);
  }

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
      addToTitleSection(aLogo);
    }
    if (menuHeader != null) {
      setMenuHeader(menuHeader);
    }
    Div title = new Div();
    title.setText(aTitle);
    addToTitleSection(title);
  }

  public void addToTitleSection(Component component) {
    component.getElement().setAttribute("slot", APP_LAYOUT_TITLE_SLOT_NAME);
    add(component);
  }

  /**
   * Sets the component to be shown before the menu in the drawer.
   * @param menuHeader
   */
  public void setMenuHeader(Component menuHeader) {
    getChildren().filter(item->PROFILE_SLOT_NAME.equals(item.getElement().getAttribute("slot"))).forEach(this::remove);
    menuHeader.getElement().setAttribute("slot", PROFILE_SLOT_NAME);
    add(menuHeader);
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
    this.getElement().setProperty("drawerVisible", visible);
  }

  public boolean isMenuVisible() {
    return this.getElement().getProperty("drawerVisible", true);
  }

  @Override
  public void configurePage(InitialPageSettings settings) {
    settings.addMetaTag("viewport", "width=device-width, initial-scale=1.0");
  }

  /**
   * Sets the toolbar title
   * @param caption
   */
  public void setCaption(String caption) {
    this.getElement().setAttribute(TITLE_ATTRIBUTE_NAME, caption);
  }
  
  /**
   * Sets the fixed attribute so it mantains the header fixed at the top 
   * so it never moves away.
   * @param fixed
   */
  public void setFixed(boolean fixed) {
    this.getElement().setAttribute("fixed", fixed);
  }

  /**
   * Sets the reveals attribute so it slides back the header when scrolling 
   * back up.
   * @param reveals
   */
  public void setReveals(boolean reveals) {
    this.getElement().setAttribute("reveals", reveals);
  }

  /**
   * Sets the swipeOpen attribute so it creates an area at the edge of the 
   * screen to swipe open the app-drawer
   * @param swipeOpen
   */
  public void setSwipeOpen(boolean swipeOpen) {
    this.getElement().setAttribute("swipeOpen", swipeOpen);
  }
  
  /**
   * Sets the persistent attribute so it will make the drawer to be always
   * opened in a non-modal way
   * @param drawerPersistent
   */
  public void setDrawerPersistent(boolean drawerPersistent) {
    this.getElement().setAttribute("drawerPersistent", drawerPersistent);
  }
  
  /**
   * Sets the drawerBelowHeader attribute so the drawer will be show below
   * the header of the applayout
   * @param drawerBelowHeader
   */
  public void setDrawerBelowHeader(boolean drawerBelowHeader) {
    this.getElement().setAttribute("drawerBelowHeader", drawerBelowHeader);
  }

}
