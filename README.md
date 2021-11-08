[![Published on Vaadin Directory](https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg)](https://vaadin.com/directory/component/app-layout-addon)
[![Stars on vaadin.com/directory](https://img.shields.io/vaadin-directory/star/app-layout-addon.svg)](https://vaadin.com/directory/component/app-layout-addon)
[![Build Status](https://jenkins.flowingcode.com/job/AppLayout-4.x-addon/badge/icon)](https://jenkins.flowingcode.com/job/AppLayout-4.x-addon)

# App Layout Add-on

Vaadin Flow Java integration of https://github.com/PolymerElements/app-layout
This addon is particularly useful if you want to create a new application with some initial support for responsiveness.

## Features

* Left side menu with hamburguer button and avatar image.
* Header with toolbar icons.
* Hierarchical menus with icons (infinite level of submenus).
* Application header with logo, title and toolbar icons.
* Menu separator with optional label.
* Support for left, middle and right click listeners.
* Menu items accept arbitrary content (such as checkboxes, or buttons)

## Online demo

[Online demo here](http://addonsv14.flowingcode.com/applayout)

## Download release

[Available in Vaadin Directory](https://vaadin.com/directory/component/app-layout-addon)

## Building and running demo

- git clone repository
- mvn clean install jetty:run

To see the demo, navigate to http://localhost:8080/

## Release notes

See [here](https://github.com/FlowingCode/AppLayoutAddon/releases)

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

AppLayout is written by Flowing Code S.A.

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
    new MenuItem("SubMenu").setIcon("build").add( 
        new MenuItem("Hello Again", "inbox",()->showContent("Hello Again!")),
        new MenuItem("And Again",()->showContent("And Again!")),
        new MenuItem("SubMenu").add(
            new MenuItem("Hello Again",()->showContent("Hello Again!")),
	    new MenuItem("And Again",()->showContent("And Again!"))
        )
    )
);
```
Toolbar icons:
```
app.setToolbarIconButtons(
    new ToolbarIconButton("Delete", "delete", ()->Notification.show("Delete action")),
    new ToolbarIconButton("Search", "search", ()->Notification.show("Search action")),
    new ToolbarIconButton("Close", "close", ()->Notification.show("Close action"))
);
```
