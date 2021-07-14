package com.flowingcode.addons.applayout.endpoint;

import java.util.List;
import java.util.stream.Collectors;

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
        return menuItemsProviders.stream().map(mip->mip.getMenuItems()).flatMap(List::stream).collect(Collectors.toList());
    }

}
