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

import com.vaadin.flow.component.HasElement;

public class AbstractLayoutDemo extends AbstractFcAppRouterLayout {

  @Override
  protected void configure(AppLayout app) {
    app.setMenuItems(new MenuItem("Item 1"), new MenuItem("Item 2"));
  }

  @Override
  public void showRouterLayoutContent(AppLayout app, HasElement content) {
    super.showRouterLayoutContent(app, content);
  }
}
