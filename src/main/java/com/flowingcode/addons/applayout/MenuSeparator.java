/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 - 2020 Flowing Code
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
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.dom.Element;

@JsModule("./fc-applayout/fc-separator.js")
@Tag("fc-separator")
public class MenuSeparator extends Component {

	private static final long serialVersionUID = 1L;

	public MenuSeparator() {}
	
	public MenuSeparator(String label) {
		Element div = new Element("div");
		div.setText(label);
		div.setAttribute("slot", "label");
		getElement().removeAllChildren();
		getElement().appendChild(div);
	}
	
	
}
