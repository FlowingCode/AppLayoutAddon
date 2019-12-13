package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.HasElement;

interface HasMenuItemIcon<T extends HasMenuItemIcon<T>> extends HasElement {

	public default String getIcon() {
		return getElement().getAttribute("icon");
	}

	public default T setIcon(String icon) {
		if (icon!=null) {
			getElement().setAttribute("icon", icon);
			getElement().removeAttribute("src");
		} else {
			getElement().removeAttribute("icon");
		}
		
		@SuppressWarnings("unchecked")
		T self = (T) this;
		return self;
	}

	public default String getImage() {
		return getElement().getAttribute("src");
	}

	public default T setImage(String imageUrl) {
		if (imageUrl!=null) {
			getElement().setAttribute("src", imageUrl);
			getElement().removeAttribute("icon");
		} else {
			getElement().removeAttribute("src");
		}
		
		@SuppressWarnings("unchecked")
		T self = (T) this;
		return self;
	}
	
}
