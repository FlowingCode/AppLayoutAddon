package com.flowingcode.addons.applayout;

/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 Flowing Code
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

import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route("")
public class DemoView extends VerticalLayout {

	private VerticalLayout container = new VerticalLayout();
	private final AppLayout app = new AppLayout("AppLayout Addon for Vaadin 10 Demo");

	public DemoView() {
		container.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		container.setSizeFull();

		this.setPadding(false);
		this.setSpacing(false);
		this.setMargin(false);

		app.setMenuItems(createMenuItems());
		
		app.setToolbarIconButtons(new MenuItem("Delete", "delete", () -> Notification.show("Delete action")),
				new MenuItem("Hide/Show Menu", "clear", () -> app.setMenuVisible(!app.isMenuVisible())),
				new MenuItem("Search", "search", () -> Notification.show("Search action")),
				new MenuItem("Close", "close", () -> Notification.show("Close action")));

		this.add(app, container);
	}

	private MenuItem[] createMenuItems() {
		MenuItem mi = new MenuItem("Say hello", "star", () -> showContent("Hello!"));
		return new MenuItem[] {mi ,
				new MenuItem("About", "cloud", () -> showContent("About")),
				new MenuItem("Clear Items", "clear", () -> app.clearMenuItems()), 
				new MenuItem("Change Text & Icon", "cloud", () -> {
					if (mi.getIcon().equals("star")) {
						mi.setIcon("cloud");
						mi.setLabel("Say hello modified");
					} else {
						mi.setIcon("star");
						mi.setLabel("Say hello");
					}
				}),
				new MenuItem("SubMenu", "build", 
						new MenuItem("Hello Again", "inbox",()->showContent("Hello Again!")),
						new MenuItem("And Again",()->showContent("And Again!")),
						new MenuItem("SubMenu",
								new MenuItem("Hello Again",()->showContent("Hello Again!")),
								new MenuItem("And Again",()->showContent("And Again!")))
						)};
	}

	private void showContent(String content) {
		container.removeAll();
		H3 label = new H3();
		label.setSizeFull();
		label.setText(content);
		PaperCard pc = new PaperCard(label, new MenuItem("Delete", () -> Notification.show("Delete action from card")),
				new MenuItem("Delete", "delete", () -> Notification.show("Delete action from card")));
		pc.setWidth("100%");
		container.add(pc);
	}
}
