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

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import dev.hilla.Nonnull;

public class MenuItemDto {

    private String label;
    @Nullable
    private String href;
    private @Nonnull List<@Nonnull MenuItemDto> children = new ArrayList<>();

    public MenuItemDto(String label, List<MenuItemDto> children) {
        this(label,null,children);
    }

    public MenuItemDto(String label, String href) {
        this(label,href,new ArrayList<>());
    }

    public MenuItemDto(String label, String href, List<MenuItemDto> children) {
        this.label = label;
        this.href = href;
        this.children = children;
    }

    public List<MenuItemDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItemDto> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }
    public String getHref() {
        return href;
    }
    public void setHref(@Nonnull String href) {
        this.href = href;
    }
    public void setLabel(@Nonnull String label) {
        this.label = label;
    }

}
