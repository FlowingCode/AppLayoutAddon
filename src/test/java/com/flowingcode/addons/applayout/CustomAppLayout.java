/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 - 2022 Flowing Code
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

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;

@SuppressWarnings("serial")
public class CustomAppLayout extends AppLayout {

  public CustomAppLayout() {
    setMenuItems(new MenuItem("Item 1"), new MenuItem("Item 2"));

    // menu header
    Div container = new Div();
    container.getElement().setAttribute("style", "text-align: center;");
    Image img = new Image("frontend/images/avatar.png", "avatar");
    img.getStyle().set("width", "80px");
    img.getStyle().set("margin-top", "20px");
    Span userTitle = new Span("User");
    userTitle.setWidthFull();
    userTitle.getStyle().set("display", "block");
    userTitle.getStyle().set("font-size", "large");
    userTitle.getStyle().set("font-weight", "bold");
    container.add(img, userTitle);
    setMenuHeader(container);

    // logo
    Image imglogo = new Image("frontend/images/applogo.png", "applogo");
    imglogo.setWidth("25px");
    addToTitleSection(imglogo);

    // title
    addToTitleSection(new Div(new Span("Test Application")));
  }

}
