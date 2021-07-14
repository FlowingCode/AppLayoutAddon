package com.flowingcode.addons.applayout.endpoint;

import java.util.List;

@FunctionalInterface
public interface MenuItemsProvider {

    List<MenuItemDto> getMenuItems();
    
}
