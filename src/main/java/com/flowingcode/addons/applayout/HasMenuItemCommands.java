/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 - 2021 Flowing Code
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

import com.flowingcode.addons.applayout.MouseClickEvent.MouseButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.shared.Registration;
import java.io.Serializable;
import java.util.EnumMap;
import org.apache.commons.lang3.tuple.Pair;

interface HasMenuItemCommands<T extends Component & HasMenuItemCommands<T>> extends HasElement {

  class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    private final EnumMap<MouseButton, Pair<Command, Registration>> commands =
        new EnumMap<>(MouseButton.class);
  }

  public default T setCommand(Command command) {
    return setCommand(MouseButton.LEFT, command);
  }

  public default T setCommand(MouseButton button, Command command) {
    Data data = ComponentUtil.getData((Component) this, HasMenuItemCommands.Data.class);
    if (data == null) {
      data = new Data();
    }

    data.commands.remove(button);
    if (command != null) {
      Registration registration =
          getElement()
              .addEventListener(
                  "mouseup",
                  ev -> {
                    command.execute();
                    ((Component)this).getElement().executeJs("this.dispatchEvent(new CustomEvent('item-clicked', {bubbles: true}))");
                  })
              .setFilter("event.button==" + button.ordinal());
      data.commands.put(button, Pair.of(command, registration));
    }

    if (data.commands.isEmpty()) {
      data = null;
    }

    ComponentUtil.setData((Component) this, HasMenuItemCommands.Data.class, data);

    @SuppressWarnings("unchecked")
    T self = (T) this;
    return self;
  }
}
