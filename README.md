# App Layout Add-on

Vaadin 10 Java integration of https://github.com/PolymerElements/app-layout
This addon is particularly usefull if you want to create a new application with some initial support for responsiveness.

## API

Creating the AppLayout:
```
AppLayout app =new AppLayout("AppLayout Addon for Vaadin 10 Demo");
```
Adding menu items:
```
    	app.setMenuItems(new MenuItem("Say hello", ()->showContent("Hello!")),
    			new MenuItem("About", ()->showContent("About"))
```
Toolbar icons:
```
    	app.setToolbarIconButtons(new MenuItem("Delete", "delete", ()->Notification.show("Delete action")),
    			new MenuItem("Search", "search", ()->Notification.show("Search action")),
    			new MenuItem("Close", "close", ()->Notification.show("Close action"))
    			);
```
Using paper-cards:
```
    	H3 label = new H3();
    	label.setSizeFull();
    	label.setText(content);
    	PaperCard pc = new PaperCard(label,new MenuItem("Delete", "delete", ()->Notification.show("Delete action from card")));
    	pc.setWidth("100%");
```

## Development instructions

Starting the test/demo server:
```
mvn jetty:run
```

This deploys demo at http://localhost:8080


