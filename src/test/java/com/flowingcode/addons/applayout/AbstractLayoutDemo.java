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

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;

public class AbstractLayoutDemo extends AbstractFcAppRouterLayout {

  @Override
  protected void configure(AppLayout app) {
    app.setMenuItems(new MenuItem("Item 1"), new MenuItem("Item 2"));

    // menu header
    Div container = new Div();
    container.getElement().setAttribute("style", "text-align: center;");
    Image img = new Image("frontend/images/avatar.png", "avatar");
    img.getStyle().set("width", "80px");
    img.getStyle().set("margin-top", "20px");
    H4 h4 = new H4("User");
    container.add(img, h4);
    app.setMenuHeader(container);

    // logo
    Image imglogo = new Image("frontend/images/applogo.png", "applogo");
    imglogo.setWidth("25px");
    app.addToTitleSection(imglogo);

    // title
    app.addToTitleSection(new Div(new Span("Test Application")));
  }

  @Override
  public void showRouterLayoutContent(AppLayout app, HasElement content) {
    super.showRouterLayoutContent(app, content);
  }
}
