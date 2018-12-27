[![Published on Vaadin Directory](https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg)](https://vaadin.com/directory/component/app-layout-addon)
[![Stars on vaadin.com/directory](https://img.shields.io/vaadin-directory/star/app-layout-addon.svg)](https://vaadin.com/directory/component/app-layout-addon)

# App Layout Add-on

Vaadin 10 Java integration of https://github.com/PolymerElements/app-layout
This addon is particularly usefull if you want to create a new application with some initial support for responsiveness.

## Features

* Left side menu with hamburguer button, and now with support for sub-menus and icons
* Toolbar icons
* Paper-cards with icons

## Online demo

[Online demo here](http://addonsv10.flowingcode.com/applayout)

## Download release

[Available in Vaadin Directory](https://vaadin.com/directory/component/app-layout-addon)

## Building and running demo

- git clone repository
- mvn clean install jetty:run

To see the demo, navigate to http://localhost:8080/

## Release notes

- **Version 1.0.0** Initial Version
- **Version 1.0.1** Fixed recursion problem
- **Version 1.0.2** Added support for sub-menus and icons in the menus
- **Version 1.0.3** Added support for changing the label and icon of a MenuItem dinamically.
- **Version 1.0.4** Added support for: placing a logo in the application header, changing icons in the toolbar, setting a custom component to the menu header (such as logged-in user account details), setting visibility of side menu and button and dynamically clearing menu items.
- **Version 1.0.5** Added support for: setting the header title and height, and configuring the header behavior on scroll, the drawer swipe-open behavior, and disabling menu items.

## Roadmap

* Support for app-grid

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:

- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

FilterableLayout is written by Flowing Code S.A.

# Developer Guide

## Getting started

Creating the AppLayout:
```
AppLayout app =new AppLayout("AppLayout Addon for Vaadin 10 Demo");
```
Adding menu items with sub-menus & icons:
```
		app.setMenuItems(new MenuItem("Say hello", "star", () -> showContent("Hello!")),
				new MenuItem("About", "cloud", () -> showContent("About")),
				new MenuItem("SubMenu", "build", 
						new MenuItem("Hello Again", "inbox",()->showContent("Hello Again!")),
						new MenuItem("And Again",()->showContent("And Again!")),
						new MenuItem("SubMenu",
								new MenuItem("Hello Again",()->showContent("Hello Again!")),
								new MenuItem("And Again",()->showContent("And Again!")))
						));
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