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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

public class MouseClickEvent<T extends Component> extends ComponentEvent<T> {

  private static final long serialVersionUID = 1L;

  public enum MouseButton {
    LEFT,
    MIDDLE,
    RIGHT;
  }

  private MouseButton button;

  public MouseClickEvent(T source, MouseButton button, boolean fromClient) {
    super(source, fromClient);
    this.button = button;
  }

  public MouseButton getButton() {
    return button;
  }
}
