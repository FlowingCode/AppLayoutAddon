package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;

@FunctionalInterface
public interface MouseClickListener<T extends Component> extends ComponentEventListener<MouseClickEvent<T>> {

}
