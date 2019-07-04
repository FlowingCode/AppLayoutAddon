package com.flowingcode.addons.applayout;

import java.net.URL;
import java.util.Optional;

import com.flowingcode.addons.applayout.MouseClickEvent.MouseButton;
import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.shared.Registration;

@Tag("fc-menuitem")
@HtmlImport("bower_components/fc-applayout/fc-menuitem.html")
@SuppressWarnings("serial")
public class MenuItemComponent extends Component implements HasEnabled, HasComponents {
	
	public MenuItemComponent() { }
	
	public MenuItemComponent(MenuItem menuItem) {
		configure(menuItem);
	}
	
	public void configure(MenuItem menuItem) {
		setLabel(menuItem.getLabel());
		setIcon(menuItem.getIcon());
		setImage(menuItem.getImageURL());
		setEnabled(menuItem.isEnabled());
	}

	public String getLabel() {
		return getElement().getAttribute("label");
	}
	
	public void setLabel(String label) {
		getElement().setAttribute("label", label);
	}
	
	public String getIcon() {
		return getElement().getAttribute("icon");
	}
	
	public void setIcon(String icon) {
		if (icon!=null) {
			getElement().setAttribute("icon", icon);
			getElement().removeAttribute("image");
		} else {
			getElement().removeAttribute("icon");
		}
	}
	
	public String getImage() {
		return getElement().getAttribute("image");
	}
	
	public void setImage(String image) {
		if (image!=null) {
			getElement().setAttribute("image", image);
			getElement().removeAttribute("icon");
		} else {
			getElement().removeAttribute("image");
		}
	}
	
	public void setImage(URL imageURL) {
		setImage(Optional.ofNullable(imageURL).map(URL::toString).orElse(null));		
	}

	public Registration addMouseClickEvent(MouseClickListener<MenuItemComponent> listener) {
		return getElement().addEventListener("item-click", ev->{
			int btnIndex = (int) ev.getEventData().getNumber("event.detail");
			MouseButton button = MouseButton.values()[btnIndex];
			listener.onComponentEvent(new MouseClickEvent<MenuItemComponent>(this, button, true));
		}).addEventData("event.detail");
	}

}
