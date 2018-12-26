package com.flowingcode.addons.applayout;

/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 Flowing Code
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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.server.Command;

/**
 * Component that renders a paper-icon-button
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@Tag("paper-icon-button")
@HtmlImport("bower_components/paper-icon-button/paper-icon-button.html")
public class PaperIconButton extends Component implements HasEnabled {

    public PaperIconButton(String icon) {
    	this.setIcon(icon);
    }

    public PaperIconButton(String icon, Command command) {
    	this.setIcon(icon);
		this.getElement().addEventListener("click", e->{
			command.execute();
		});
    }

    public void setIcon(String icon) {
    	this.getElement().setAttribute("icon", icon);
    
    }
    
    
}
