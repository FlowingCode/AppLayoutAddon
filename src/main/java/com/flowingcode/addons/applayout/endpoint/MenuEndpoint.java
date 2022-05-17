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
package com.flowingcode.addons.applayout.endpoint;

import java.util.List;
import java.util.stream.Collectors;
import com.flowingcode.addons.applayout.MenuItem;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;

@Endpoint
@AnonymousAllowed
public class MenuEndpoint {
    
    List<MenuItemsProvider> menuItemsProviders;

    public MenuEndpoint(List<MenuItemsProvider> menuItemsProviders) {
        this.menuItemsProviders = menuItemsProviders;
    }

    public @Nonnull List<@Nonnull MenuItemDto> getMenuItems() {
        List<MenuItem> menuItems = menuItemsProviders.stream().map(MenuItemsProvider::getMenuItems).flatMap(List::stream).collect(Collectors.toList());
        return convertMenuItems(menuItems);
    }

    private List<MenuItemDto> convertMenuItems(List<MenuItem> menuItems) {
        return menuItems.stream().map(mi->new MenuItemDto(mi.getLabel(),mi.getHref(),extractMenuItems(mi))).collect(Collectors.toList());
    }

    private List<MenuItemDto> extractMenuItems(MenuItem mi) {
        return convertMenuItems(mi.getChildren().filter(MenuItem.class::isInstance).map(MenuItem.class::cast).collect(Collectors.toList()));
    }

}
