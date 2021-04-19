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
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.shared.Registration;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Component that renders the app drawer
 *
 * @author mlopez
 */
@SuppressWarnings("serial")
@Tag("app-drawer")
@HtmlImport("bower_components/app-layout/app-drawer/app-drawer.html")
@HtmlImport("bower_components/iron-scroll-target-behavior/iron-scroll-target-behavior.html")
@NpmPackage(value = "@polymer/app-layout", version = AppLayout.NPM_VERSION)
@JsModule("@polymer/app-layout/app-drawer/app-drawer.js")
public class AppDrawer extends Component implements HasComponents {

  private final PaperListbox pm = new PaperListbox(Collections.emptyList());
  private final Component header;

  public AppDrawer(String title) {
    this(new H4(title));
    header.getElement().setAttribute("style", "text-align:center");
  }

  public AppDrawer(Component headerComponent) {
    this.header = headerComponent;
    getElement().setAttribute("id", "drawer");
    setSwipeOpen(true);

    Registration[] r = new Registration[1];
    r[0] =
        getElement()
            .addEventListener(
                "app-drawer-transitioned",
                ev -> {
                  // need to adjust the height after the drawer has been rendered
                  pm.getElement()
                      .executeJs(
                          "this.style.height='calc(100% - '+($0.scrollHeight+16)+'px)'", header);
                  r[0].remove();
                });

    removeAll();
  }

  public void setSwipeOpen(boolean swipeOpen) {
    getElement().setAttribute("swipe-open", swipeOpen);
  }

  @Override
  public void add(Component... components) {
    for (Component c : components) {
      if (c instanceof MenuItem) {
        pm.add(c);
      } else {
        HasComponents.super.add(components);
      }
    }
  }

  @Override
  public void remove(Component... components) {
    for (Component c : components) {
      if (c instanceof MenuItem) {
        pm.removeAll();
      } else {
        HasComponents.super.remove(components);
      }
    }
  }

  @Override
  public void removeAll() {
    HasComponents.super.removeAll();
    this.add(header);
    this.add(pm);
  }

  public void setMenuItems(Collection<? extends Component> menuItems) {
    pm.removeAll();
    menuItems.stream().forEach(pm::add);
  }

  /** Close the app-drawer. */
  public void close() {
    getUI().ifPresent(ui -> ui.getPage().executeJs("$0.close()", this));
  }

  static Optional<AppDrawer> findAppDrawer(Component component) {
    while (component != null) {
      if (component instanceof AppDrawer) {
        return Optional.of((AppDrawer) component);
      } else {
        component = component.getParent().orElse(null);
      }
    }
    return Optional.empty();
  }
}
