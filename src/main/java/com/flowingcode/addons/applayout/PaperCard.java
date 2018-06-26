package com.flowingcode.addons.applayout;

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

/**
 * Component that renders a paper-card
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/paper-card/paper-card.html")
@Tag("paper-card")
public class PaperCard extends Component implements HasComponents, HasSize, HasStyle, ThemableLayout {

    private final Div cardContentDiv = new Div();

    private final Div cardActionsDiv = new Div();

    protected PaperCard() {
        this(null);
    }

    public PaperCard(final Component cardContent, final MenuItem... cardActions) {
        cardContentDiv.setClassName("card-content");
        cardActionsDiv.setClassName("card-actions");
        this.add(cardContentDiv);

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
            this.add(cardActionsDiv);
        }
    }

    public void setCardContent(final Component content) {
        cardContentDiv.removeAll();
        cardContentDiv.add(content);
    }

}
