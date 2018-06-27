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
	
    public DemoView() {
    	container.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    	container.setSizeFull();
    	
    	this.setPadding(false);
    	this.setSpacing(false);
    	this.setMargin(false);
    	
    	AppLayout app =new AppLayout("AppLayout Addon for Vaadin 10 Demo");
    	app.setMenuItems(new MenuItem("Say hello", ()->showContent("Hello!")),
    			new MenuItem("About", ()->showContent("About"))
//    			new MenuItem(
//    					new MenuItem("Hello Again", ()->Notification.show("Hello, World!",3000,Position.BOTTOM_END)),
//    					new MenuItem("And Again", ()->Notification.show("Hello, World!",3000,Position.BOTTOM_END))
//    					)
    			);
    	app.setToolbarIconButtons(new MenuItem("Delete", "delete", ()->Notification.show("Delete action")),
    			new MenuItem("Search", "search", ()->Notification.show("Search action")),
    			new MenuItem("Close", "close", ()->Notification.show("Close action"))
    			);
    	
    	this.add(app,container);
    }
    
    private void showContent(String content) {
    	container.removeAll();
    	H3 label = new H3();
    	label.setSizeFull();
    	label.setText(content);
    	PaperCard pc = new PaperCard(label,new MenuItem("Delete", ()->Notification.show("Delete action from card")),
    			new MenuItem("Delete", "delete", ()->Notification.show("Delete action from card")));
    	pc.setWidth("100%");
    	container.add(pc);
    }
}
