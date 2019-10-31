/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 - 2019 Flowing Code
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.flowingcode.addons.applayout;

import java.util.Optional;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLayout;

/**
 * Convenience class for using an AppLayout as a parent layout in a Flow application.
 * Basic usage involves extending this class and implementing
 * the {@code configure} method.
 */
public abstract class AbstractFcAppRouterLayout extends Div implements RouterLayout {

	private static final long serialVersionUID = 1L;
	
	private AppLayout app;

	public AbstractFcAppRouterLayout() {
		setSizeFull();
		getElement().getStyle().set("display", "flex");
		getElement().getStyle().set("flex-direction", "column");
		app = new AppLayout("");
		app.setHeight("32px");
		app.addClassName("compact");
		app.setFixed(true);
		app.setSwipeOpen(false);
		add(app);
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
	    configure(app);
	}

	protected abstract void configure(AppLayout app);

	@Override
	public final void showRouterLayoutContent(HasElement content) {
		showRouterLayoutContent(app,content);
	}
	
	protected void showRouterLayoutContent(AppLayout app, HasElement content) {
		content.getElement().getStyle().set("flex-grow", "1");
		content.getElement().getStyle().set("display", "flex");
		content.getElement().getStyle().set("flex-direction", "column");
		app.setCaption(getCaption(content));
		RouterLayout.super.showRouterLayoutContent(content);
	}

	private static String getCaption(HasElement content) {
		if (content instanceof HasDynamicTitle) {
			return ((HasDynamicTitle)content).getPageTitle();
		} else {
			return Optional.ofNullable(content.getClass().getAnnotation(PageTitle.class))
					.map(PageTitle::value).orElse("");
		}
	}

}