/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 - 2022 Flowing Code
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

import com.flowingcode.addons.applayout.MenuItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import com.vaadin.flow.dom.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * Component that renders a paper-card
 *
 * @author mlopez
 */
@SuppressWarnings("serial")
@NpmPackage(value = "@polymer/paper-card", version = "3.0.1")
@JsModule("@polymer/paper-card/paper-card.js")
@Tag("paper-card")
public class PaperCard extends Component implements HasSize, HasStyle, ThemableLayout {

  private final Div cardContentDiv = new Div();

  private final Div cardActionsDiv = new Div();

  @SuppressWarnings("squid:S1604")
  private final HasComponents hasComponentsVersion =
      new HasComponents() {
        @Override
        public Element getElement() {
          return PaperCard.this.getElement();
        }
      };

  public PaperCard() {
    this(null);
  }

  public PaperCard(final Component cardContent, final MenuItem... cardActions) {
    cardContentDiv.setClassName("card-content");
    cardActionsDiv.setClassName("card-actions");

    hasComponentsVersion.add(cardContentDiv);

    if (cardContent != null) {
      setCardContent(cardContent);
    }

    setCardActions(cardActions);
  }

  public void setCardActions(final MenuItem... cardActions) {
    if (cardActions.length > 0) {
      final List<Component> buttons = new ArrayList<>();
      for (final MenuItem menuItem : cardActions) {
        if (menuItem.getIcon() != null) {
          buttons.add(menuItem);
        } else {
          buttons.add(menuItem);
        }
      }
      final Div inner = new Div();
      cardActionsDiv.add(inner);
      inner.addClassNames("horizontal", "justified");
      buttons.forEach(inner::add);
      hasComponentsVersion.add(cardActionsDiv);
    }
  }

  public void setCardContent(final Component content) {
    cardContentDiv.removeAll();
    cardContentDiv.add(content);
  }
}
