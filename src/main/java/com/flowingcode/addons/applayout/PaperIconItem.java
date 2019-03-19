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
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.server.Command;

/**
 * Component that renders a paper-item
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@HtmlImport("bower_components/paper-item/paper-icon-item.html")
@Tag("paper-icon-item")
public class PaperIconItem extends Component implements HasComponents, HasText, HasSize {
	
	private IronIcon ironIcon;
	private Text text;
	
	public PaperIconItem(String title, String icon) {
		this(title, icon, null ,null);
	}

	public PaperIconItem(String title, String icon, Command command) {
		this(title, icon, command, null);
	}

	public PaperIconItem(String title, String icon, Command command, AppDrawer appDrawer) {
		this.setText("");
		this.ironIcon = new IronIcon(icon);
		add(ironIcon);
		this.text = new Text(title);
		add(text);
		if (command!=null) {
			this.getElement().addEventListener("click", e->{
				command.execute();
				if (appDrawer!=null)
					appDrawer.getUI().ifPresent(ui->ui.getPage().executeJavaScript("" + appDrawer.getId().get() + ".toggle()"));
			});
		}
	}
	
	public void setTitle(String title) {
		this.text.setText(title);
	}
	
	public void setIcon(String icon) {
		this.ironIcon.setIcon(icon);
	}

}
