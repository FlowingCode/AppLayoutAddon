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

interface HasMenuItemIcon<T extends HasMenuItemIcon<T>> extends HasElement {

	public default String getIcon() {
		return getElement().getAttribute("icon");
	}

	public default T setIcon(String icon) {
		if (icon!=null) {
			getElement().setAttribute("icon", icon);
			getElement().removeAttribute("src");
		} else {
			getElement().removeAttribute("icon");
		}
		
		@SuppressWarnings("unchecked")
		T self = (T) this;
		return self;
	}

	public default String getImage() {
		return getElement().getAttribute("src");
	}

	public default T setImage(String imageUrl) {
		if (imageUrl!=null) {
			getElement().setAttribute("src", imageUrl);
			getElement().removeAttribute("icon");
		} else {
			getElement().removeAttribute("src");
		}
		
		@SuppressWarnings("unchecked")
		T self = (T) this;
		return self;
	}
	
}
