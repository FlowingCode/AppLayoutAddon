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
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.server.Command;

/**
 * Component that renders a paper-button
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/paper-button/paper-button.html")
@NpmPackage(value = "@polymer/paper-button", version = "3.0.1")
@JsModule("@polymer/paper-button/paper-button.js")
@Tag("paper-button")
public class PaperButton extends Component {
	
    public PaperButton(String label) {
    	setLabel(label);
    }

    public PaperButton(String label, Command command) {
    	this.setLabel(label);
		this.getElement().addEventListener("click", e->command.execute());
    }

    public void setLabel(String label) {
    	this.getElement().setText(label);
    }
    
    
}
