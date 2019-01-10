package com.flowingcode.addons.applayout;

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


import java.util.ArrayList;
import java.util.List;

import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import com.vaadin.flow.dom.Element;

/**
 * Component that renders a paper-card
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/paper-card/paper-card.html")
@Tag("paper-card")
public class PaperCard extends Component implements HasSize, HasStyle, ThemableLayout {

    private final Div cardContentDiv = new Div();

    private final Div cardActionsDiv = new Div();
    
    private final HasComponents hasComponentsVersion = new HasComponents() {
		@Override
		public Element getElement() {
			return PaperCard.this.getElement();
		}
    };

    protected PaperCard() {
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
                    buttons.add(new PaperIconButton(menuItem.getIcon(), menuItem.getCommand()));
                } else {
                    buttons.add(new PaperButton(menuItem.getLabel(), menuItem.getCommand()));
                }
            }
            final Div inner = new Div();
            cardActionsDiv.add(inner);
            inner.addClassNames("horizontal", "justified");
            buttons.forEach(b -> inner.add(b));
            hasComponentsVersion.add(cardActionsDiv);
        }
    }
    
    public void setCardContent(final Component content) {
        cardContentDiv.removeAll();
        cardContentDiv.add(content);
    }

}
