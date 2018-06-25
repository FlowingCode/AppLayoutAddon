package com.flowingcode.addons.applayout;

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
