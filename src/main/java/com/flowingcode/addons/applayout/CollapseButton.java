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



import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;

/**
 * @author mlope
 *
 */
@SuppressWarnings("serial")
//@NpmPackage(value="@polymer/iron-flex-layout")
//@NpmPackage(value="@polymer/iron-iconset-svg")
@NpmPackage(value="@polymer/iron-icon", version = "^3.0.1")
@NpmPackage(value="@polymer/iron-collapse", version = "^3.0.1")
@JsModule("./iron-collapse-button/iron-collapse-button.js")
@Tag("iron-collapse-button")
public class CollapseButton extends Component implements HasComponents {
	
	public CollapseButton(String label, PaperItem[] items) {
		this(label, null, null, items);
	}

	@SuppressWarnings("squid:S2589")
	public CollapseButton(String label, String icon, String image, Component...items) {		
		if (icon==null && image==null) {
			PaperItem pi = new PaperItem(label);
			configureAndAddItem(pi);
		} else if (image!=null) {
			PaperIconItem pi = new PaperIconItem(label, image);
			configureAndAddItem(pi);
		} else if (icon!=null) {
			PaperIconItem pi = new PaperIconItem(label, icon);
			configureAndAddItem(pi);
		}
		Div div = new Div();
		div.getElement().setAttribute("slot", "collapse-content");
		div.setClassName("sub-menu");
		div.add(items);
		this.add(div);
	}

	private <T extends Component & HasSize> void configureAndAddItem(T pi) {
		pi.getElement().setAttribute("slot", "collapse-trigger");
		pi.setWidth("100%");
		this.add(pi);			
	}
	
}
