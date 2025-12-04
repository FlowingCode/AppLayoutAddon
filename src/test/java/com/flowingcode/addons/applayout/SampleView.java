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

import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.junit.Ignore;

@SuppressWarnings("serial")
@Route(value = "view", layout = CustomAppLayout.class)
@Uses(AppLayout.class)
@Ignore
public class SampleView extends Div implements BeforeEnterObserver {

  {
    add(new Span("Hello world"));
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    if (event.getLocation().getQueryParameters().getParameters().containsKey("theme")) {
      String theme = event.getLocation().getQueryParameters().getParameters().get("theme").get(0);
      if ("dark".equalsIgnoreCase(theme)) {
        getElement().executeJs("document.documentElement.setAttribute('theme', 'dark');");
      }
    }
  }
}
