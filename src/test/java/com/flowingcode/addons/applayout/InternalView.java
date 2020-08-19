package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route("internal-view")
public class InternalView extends Div {

	public InternalView() {
		add(new Span("Internal view"));
	}
}
