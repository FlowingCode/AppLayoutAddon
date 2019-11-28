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



import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.server.Command;

/**
 * Component that renders a paper-item
 *
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/paper-item/paper-item.html")
@NpmPackage(value = "@polymer/paper-item", version = "3.0.1")
@JsModule("@polymer/paper-item/paper-item.js")
@Tag("paper-item")
public class PaperItem extends Component implements HasEnabled, HasText, HasSize {

	public PaperItem(String title) {
		this(title, null,null);
	}

	public PaperItem(String label, Command command) {
		this(label, command, null);
	}

	public PaperItem(String title, Command command, AppDrawer appDrawer) {
		setText(title);
		if (command!=null) {
			this.getElement().addEventListener("click", e->{
				command.execute();
				Optional.ofNullable(appDrawer).ifPresent(AppDrawer::close);
			});
		}
	}

}
