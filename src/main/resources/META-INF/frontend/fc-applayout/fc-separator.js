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
import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';

import {ThemableMixin} from '@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js';

import "@vaadin/flow-frontend/fc-applayout/paper-divider.js";

class MenuSeparator extends ThemableMixin(PolymerElement) {
	static get is() { return 'fc-separator'; }
	
	static get template() {

		return html`
		<style>
			:host ::slotted(*) {
				font-size: 80%;
    			padding-left: 8px;
			}
		</style>		
		<paper-divider></paper-divider>
		<div><slot name="label"></slot></div>
	`;}

}
	
customElements.define(MenuSeparator.is, MenuSeparator);
