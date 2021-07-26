package com.flowingcode.addons.applayout.endpoint;

import java.util.List;
import java.util.stream.Collectors;

import com.flowingcode.addons.applayout.MenuItem;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.connect.Endpoint;

@Endpoint
@AnonymousAllowed
public class MenuEndpoint {
    
    List<MenuItemsProvider> menuItemsProviders;

    public MenuEndpoint(List<MenuItemsProvider> menuItemsProviders) {
        this.menuItemsProviders = menuItemsProviders;
    }

    public List<MenuItemDto> getMenuItems() {
        List<MenuItem> menuItems = menuItemsProviders.stream().map(MenuItemsProvider::getMenuItems).flatMap(List::stream).collect(Collectors.toList());
        return convertMenuItems(menuItems);
    }

    private List<MenuItemDto> convertMenuItems(List<MenuItem> menuItems) {
        return menuItems.stream().map(mi->new MenuItemDto(mi.getLabel(),mi.getHref(),extractMenuItems(mi))).collect(Collectors.toList());
    }

    private List<MenuItemDto> extractMenuItems(MenuItem mi) {
        return convertMenuItems(mi.getChildren().filter(MenuItem.class::isInstance).map(MenuItem.class::cast).collect(Collectors.toList()));
    }

}
