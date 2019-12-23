package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route(value="view", layout = AbstractLayoutDemo.class)
public class AbstractLayoutView extends Div {

	{add(new Span("Hello world"));}
	
}
