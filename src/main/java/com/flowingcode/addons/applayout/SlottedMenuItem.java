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

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;

/** Menu item component. */
@SuppressWarnings("serial")
public abstract class SlottedMenuItem extends Component {

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    if (getParent().orElse(null) instanceof SlottedMenuItem) {
      getElement().setAttribute("slot", "menu-item");
    } else {
      if ("menu-item".equals(getElement().getAttribute("slot"))) {
        getElement().removeAttribute("slot");
      }
    }
    super.onAttach(attachEvent);
  }
}
