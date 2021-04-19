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
package com.flowingcode.addons.applayout.menu;

import com.flowingcode.addons.applayout.AppDrawer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.server.Command;
import java.util.Optional;

/**
 * Component that renders a paper-item
 *
 * @author mlopez
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/paper-item/paper-icon-item.html")
@NpmPackage(value = "@polymer/paper-item", version = "3.0.1")
@JsModule("@polymer/paper-item/paper-icon-item.js")
@Tag("paper-icon-item")
class PaperIconItem extends Component implements HasComponents, HasText, HasSize {

  private com.vaadin.flow.component.icon.IronIcon ironIcon;
  private Text text;

  public PaperIconItem(String title) {
    this.setText("");
    this.text = new Text(title);
    add(text);
  }

  public void addCommand(Command command) {
    if (command != null) {
      this.getElement()
          .addEventListener(
              "click",
              e -> {
                command.execute();
                findAppDrawer(this).ifPresent(AppDrawer::close);
              });
    }
  }

  public void setTitle(String title) {
    this.text.setText(title);
  }

  public void setIcon(String icon) {
    withIronIcon(icon != null)
        .ifPresent(
            e -> {
              e.removeAttribute("src");
              e.setAttribute("icon", icon);
            });
  }

  public void setImage(String image) {
    withIronIcon(image != null)
        .ifPresent(
            e -> {
              ;
              e.removeAttribute("icon");
              e.setAttribute("src", image);
            });
  }

  private Optional<Element> withIronIcon(boolean create) {
    if (create) {
      if (this.ironIcon == null) {
        this.ironIcon = new com.vaadin.flow.component.icon.IronIcon("", "");
        ironIcon.getElement().setAttribute("slot", "item-icon");
        add(ironIcon);
      }
      return Optional.of(ironIcon.getElement());
    } else {
      if (this.ironIcon != null) {
        remove(ironIcon);
        this.ironIcon = null;
      }
      return Optional.empty();
    }
  }

  private static Optional<AppDrawer> findAppDrawer(Component component) {
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
