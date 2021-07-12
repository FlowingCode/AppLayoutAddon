package com.flowingcode.addons.applayout;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.server.connect.Endpoint;

@Endpoint
public class MenuEndpoint {
    
    public List<MenuItem> getMenuItems() {
        return new ArrayList<>();
    }

}
