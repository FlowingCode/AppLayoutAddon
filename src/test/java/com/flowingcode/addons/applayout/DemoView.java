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
package com.flowingcode.addons.applayout;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.flowingcode.addons.applayout.MouseClickEvent.MouseButton;
import com.flowingcode.addons.applayout.menu.PaperCard;
import com.flowingcode.addons.applayout.menu.PaperToggle;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route("")
@HtmlImport("frontend://styles/shared-styles.html")
@CssImport("./styles/shared-styles.css")
public class DemoView extends VerticalLayout {

	private VerticalLayout container = new VerticalLayout();
	private final AppLayout app = new AppLayout(createLogoImage(), createAvatarComponent(), "AppLayout Vaadin 14 Demo");
	private final ToolbarIconButton miSettings = new ToolbarIconButton("Settings", "settings", this::openSettings);

	private final DemoSettings settings = new DemoSettings();

	public DemoView() {
		container.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		container.setSizeFull();

		this.setPadding(false);
		this.setSpacing(false);
		this.setMargin(false);

		app.setMenuItems(createMenuItems());
		
		app.setToolbarIconButtons(miSettings);
		this.add(app, container);

		settings.setSwipeOpen(true);
		settings.setMenuVisible(true);
		settings.setReveals(true);
		applySettings();

		showHamletContent();
	}

	//apply the current settings
	private void applySettings() {
		app.setMenuVisible(settings.isMenuVisible());
		app.setSwipeOpen(settings.isSwipeOpen());
		app.setFixed(settings.isFixed());
		app.setReveals(settings.isReveals());

		if (settings.isCompact()) {
			app.addClassName("compact");
			app.setHeight("32px");
		} else {
			app.removeClassName("compact");
			app.setHeight("64px");
		}
	}

	private void openSettings() {
		Dialog dialog = new Dialog();
		H3 title = new H3("Demo settings");
		title.getStyle().set("margin-top", "0");
		dialog.add(title);

		Checkbox cbMenuVisible = new Checkbox("Menu visible");
		Checkbox cbSwipeOpen = new Checkbox("Swipe Open");
		Checkbox cbFixed = new Checkbox("Fixed");
		Checkbox cbReveals = new Checkbox("Reveals");
		Checkbox cbCompact = new Checkbox("Compact");

		cbMenuVisible.getElement().setAttribute("title", "Toggle visibility of the hamburguer icon.");
		cbSwipeOpen.getElement().setAttribute("title", "When enabled, you can open the menu by swiping the left border of the screen.");
		cbFixed.getElement().setAttribute("title", "When enabled, the header is fixed at the top so it never moves away.");
		cbReveals.getElement().setAttribute("title", "When enabled, the header slides back when scrolling back up.");
		cbCompact.getElement().setAttribute("title", "When enabled, the height of the header is set to 32px.");

		Binder<DemoSettings> binder = new Binder<>();
		binder.forField(cbMenuVisible).bind(DemoSettings::isMenuVisible, DemoSettings::setMenuVisible);
		binder.forField(cbSwipeOpen).bind(DemoSettings::isSwipeOpen, DemoSettings::setSwipeOpen);
		binder.forField(cbFixed).bind(DemoSettings::isFixed, DemoSettings::setFixed);
		binder.forField(cbReveals).bind(DemoSettings::isReveals, DemoSettings::setReveals);
		binder.forField(cbCompact).bind(DemoSettings::isCompact, DemoSettings::setCompact);
		binder.setBean(this.settings);

		VerticalLayout content = new VerticalLayout(
				cbMenuVisible,
				cbSwipeOpen,
				cbFixed,
				cbReveals,
				cbCompact);
		content.setSpacing(false);

		HorizontalLayout buttons = new HorizontalLayout();
		Button btnOk = new Button("OK",ev -> {
			applySettings();
			dialog.close();
		});

		Button btnCancel = new Button("Cancel", ev -> dialog.close());
		btnOk.getElement().setAttribute("theme", "primary");
		buttons.setSpacing(true);
		buttons.add(btnOk, btnCancel);
		buttons.setSpacing(true);

		dialog.add(content, buttons);
		dialog.setSizeUndefined();
		dialog.open();
	}

	private Image createLogoImage() {
		Image img = new Image("/frontend/images/applogo.png","applogo");
		img.addClassName("applogo");
		return img;
	}

	private Component createAvatarComponent() {
		Div container = new Div();
		container.getElement().setAttribute("style", "text-align: center;");
		Image img = new Image("/frontend/images/avatar.png","avatar");
		img.getStyle().set("width", "80px");
		img.getStyle().set("margin-top", "20px");
		H4 h4 = new H4("User");
		container.add(img,h4);
		return container;
	}
	
	private void toggleSettings(MenuItem toggleSettings) {
		settings.setEnabled(!settings.isEnabled());
		miSettings.setEnabled(settings.isEnabled());
		app.setToolbarIconButtons(miSettings);
		if (settings.isEnabled())  {
			toggleSettings.setLabel("Disable settings");
		} else {
			toggleSettings.setLabel("Enable settings");
		}		
	}

	private Component[] createMenuItems() {

		MenuItem miHello = new MenuItem("More content", () -> showContent("Hello!")).setIcon("settings");

		MenuItem miToggleSettings = new MenuItem().setIcon("settings");
		miToggleSettings.setCommand(() -> toggleSettings(miToggleSettings));
		toggleSettings(miToggleSettings);

		this.getElement().getStyle().set("--icon-spacing", "normal");
		
		return new Component[] {
				
				//left, middle and right commands
				new MenuItem("Click", VaadinIcon.POINTER)
					.setCommand(MouseButton.LEFT, ()->Notification.show("LEFT click"))
					.setCommand(MouseButton.MIDDLE, ()->Notification.show("MIDDLE click"))
					.setCommand(MouseButton.RIGHT, ()->Notification.show("RIGHT click")),
				
				new MenuItem("No icon"),
				
				new MenuItem("No icon, spaced").configure(mi->mi.setIconSpacing(true)),
				
				//menu item with custom content
				new MenuItem("Toggle").configure(mi->mi.add(new PaperToggle())),
					
				new MenuItem("Toggle", VaadinIcon.BACKSPACE).configure(mi->mi.add(new PaperToggle())),
				
				new MenuItem("Toggle", "fc-menuitem-icons:empty").configure(mi->mi.add(new PaperToggle())),
				
				//icon as VaadinIcon enum
				new MenuItem("Content", VaadinIcon.BOOK, () -> showHamletContent())
					.setCommand(MouseButton.MIDDLE, ()->{
						getUI().ifPresent(ui->ui.getPage().executeJs("window.open(window.location.href, '_blank')"));
					}),
					miToggleSettings,
				miHello,
				new MenuItem("About", "cloud", () -> showContent("About")), //icon as string
				new MenuItem("Clear Items", "clear", () -> app.clearMenuItems()),
				new MenuItem("Change Text & Icon", "cloud", () -> {
					if (miHello.getIcon().equals("star")) {
						miHello.setIcon("cloud");
						miHello.setLabel("Say hello modified");
					} else {
						miHello.setIcon("star");
						miHello.setLabel("Say hello");
					}
				}),
				new MenuItem("SubMenu")
					.setIcon("build")
					.add(
						new MenuItem("Hello Again", "inbox", ()->showContent("Hello Again!")),
						new MenuItem("And Again",()->showContent("And Again!")),
						new MenuItem("SubMenu")
							.add(new MenuItem("Hello Again",()->showContent("Hello Again!")))
							.add(new MenuItem("And Again",()->showContent("And Again!")))
						),
					
				new MenuSeparator(),

				new MenuItem("Item 1"),
				new MenuItem("Item 2"),
				new MenuItem("Item 3"),
				new MenuItem("Item 4"),
				new MenuItem("Item 5"),
				new MenuItem("Item 6"),
				new MenuItem("Item 7"),
				new MenuItem("Item 8"),
				new MenuItem("Item 9"),
				new MenuItem("Item 10"),
				new MenuItem("Item 11"),
				new MenuItem("Item 12")
			};
	}

	private void showContent(String content) {
		container.setClassName("");
		container.removeAll();
		H3 label = new H3();
		label.setSizeFull();
		label.setText(content);
		PaperCard pc = new PaperCard(label, new MenuItem("Delete", () -> Notification.show("Delete action from card")),
				new MenuItem("Delete", () -> Notification.show("Delete action from card")).setIcon("delete"));
		pc.setWidth("100%");
		container.add(pc);
	}

	private void showHamletContent() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("hamlet");
		String text = new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n"));

		container.removeAll();
		container.setClassName("hamlet");
		for (String block : text.split("\n\n")) {
			if (block.startsWith("$")) {

			} else if (block.startsWith("[")) {
				PaperCard card = new PaperCard();
				card.setCardContent(new Span(block.substring(1, block.indexOf("]"))));
				card.getElement().setAttribute("elevation", "0");
				container.add(card);
			} else {
				PaperCard card = new PaperCard();
				String ss[] = block.split("\\.",2);
				card.setCardContent(new Div(new H5(ss[0]), new Span(ss[1])));
				if (ss[0].equals("Claudius")) {
					container.setHorizontalComponentAlignment(Alignment.END, card);
					card.addClassName("claudius");
				} else {
					container.setHorizontalComponentAlignment(Alignment.START, card);
				}
				container.add(card);
			}
		}
	}

}

