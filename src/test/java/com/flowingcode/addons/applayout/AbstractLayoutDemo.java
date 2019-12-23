package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.HasElement;

public class AbstractLayoutDemo extends AbstractFcAppRouterLayout{

	@Override
	protected void configure(AppLayout app) {
		app.setMenuItems(
			new MenuItem("Item 1"),
			new MenuItem("Item 2")
		);		
	}

	@Override
	public void showRouterLayoutContent(AppLayout app, HasElement content) {
		super.showRouterLayoutContent(app, content);
	}
	
}
