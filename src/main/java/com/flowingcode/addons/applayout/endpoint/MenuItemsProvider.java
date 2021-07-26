package com.flowingcode.addons.applayout.endpoint;

import java.util.List;

import com.flowingcode.addons.applayout.MenuItem;

@FunctionalInterface
public interface MenuItemsProvider {

    List<MenuItem> getMenuItems();
    
}
