package com.flowingcode.addons.applayout;

import java.util.EnumMap;

import org.apache.commons.lang3.tuple.Pair;

import com.flowingcode.addons.applayout.MouseClickEvent.MouseButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.shared.Registration;

interface HasMenuItemCommands<T extends Component & HasMenuItemCommands<T>> extends HasElement {

	class Data {
		private final EnumMap<MouseButton, Pair<Command, Registration>> commands = new EnumMap<>(MouseButton.class);
	}
		
	public default T setCommand(Command command) {
		return setCommand(MouseButton.LEFT, command);
	}

	public default T setCommand(MouseButton button, Command command) {
		Data data = ComponentUtil.getData((Component)this, HasMenuItemCommands.Data.class);
		if (data==null) {
			data = new Data();
		}
						
		data.commands.remove(button);
		if (command!=null) {
			Registration registration = getElement().addEventListener("mouseup", ev->{
				command.execute();
				AppDrawer.findAppDrawer((Component)this).ifPresent(AppDrawer::close);
			}).setFilter("event.button=="+button.ordinal());
			data.commands.put(button, Pair.of(command, registration));			
		}
		
		if (data.commands.isEmpty()) {
			data = null;			
		}
		
		ComponentUtil.setData((Component)this, HasMenuItemCommands.Data.class, data);
		
		@SuppressWarnings("unchecked")
		T self = (T) this;
		return self;
	}
	
}
