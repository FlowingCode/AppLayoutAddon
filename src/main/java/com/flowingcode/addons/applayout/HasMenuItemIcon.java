package com.flowingcode.addons.applayout;

import com.vaadin.flow.component.HasElement;

interface HasMenuItemIcon<T extends HasMenuItemIcon<T>> extends HasElement {

	public default String getIcon() {
		return getElement().getAttribute("icon");
	}

	public default T setIcon(String icon) {
		if (icon!=null) {
			getElement().setAttribute("icon", icon);
			getElement().removeAttribute("image");
		} else {
			getElement().removeAttribute("icon");
		}
		
		@SuppressWarnings("unchecked")
		T self = (T) this;
		return self;
	}

	public default String getImage() {
		return getElement().getAttribute("image");
	}

	public default T setImage(String imageUrl) {
		if (imageUrl!=null) {
			getElement().setAttribute("image", imageUrl);
			getElement().removeAttribute("icon");
		} else {
			getElement().removeAttribute("image");
		}
		
		@SuppressWarnings("unchecked")
		T self = (T) this;
		return self;
	}
	
}
