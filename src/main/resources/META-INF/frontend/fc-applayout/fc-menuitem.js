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
import {} from '@polymer/polymer/lib/elements/dom-if.js';

import {ThemableMixin} from '@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js';
import "@polymer/paper-item/paper-icon-item.js";
import "@vaadin/flow-frontend/iron-collapse-button/iron-collapse-button.js";	

class MenuItem extends ThemableMixin(PolymerElement) {
	static get is() { return 'fc-menuitem'; }
	
	static get template() {
		return html`
		<style>
			:host {
				cursor: pointer;
				display: block;
				--paper-item-disabled-color: var(--lumo-disabled-text-color);
			}
			:host(.iron-selected) #item {
				font-weight: var(--paper-item-selected-weight, bold);
			}
			:host #item {
				width: 100%;
				display: flex;
			}
			:host > iron-collapse-button {
				background: inherit;
			}
			:host #label {
				flex-grow: 1
			}
			:host a#label {
				color: inherit;
				text-decoration: none
			}
		</style>
	         	
	    <iron-iconset-svg name="fc-menuitem-icons" size="24">
			<svg><defs>
			<g id="empty"></g>
			</defs></svg>
		</iron-iconset-svg>
	
		<iron-collapse-button no-icons="true">
			<paper-icon-item id="item" slot="collapse-trigger" role="option" disabled="[[disabled]]">
				<iron-icon src="[[src]]" icon="[[icon]]" slot="item-icon"></iron-icon>
				<dom-if if="[[href]]" restamp>
					<template>
						<a router-link href="{{href}}" id="label" onclick="getRootNode().host.__closeDrawer()">[[label]]</a>
					</template>
				</dom-if>
				<dom-if if="{{!href}}" restamp>
					<template>
						<span id="label">[[label]]</span>
					</template>
				</dom-if>
				<slot></slot>
			</paper-icon-item>
			<div slot="collapse-content" class="sub-menu">
				<slot name="menu-item"></slot>
			</div>
		</iron-collapse-button>
	`;}
 		
  static get properties() {
		return {
			key: String,
			label : String,
			href: {
				type: String,
				notify: true,
				value: null,
			},
			src : {
				type: String,
				reflectToAttribute: true
			},
			icon  : {
				type: String,
				reflectToAttribute: true
			},
			disabled: Boolean,
			hasIcon: {
				type: Boolean,
				reflectToAttribute: true,
				computed: '__hasIcon(src,icon)',
				observer: '__hasIconChanged'
			},
			isSubmenu: {
				type: Boolean,
				reflectToAttribute: true,
			}
		}
	}
	
	constructor() {
		super();
		var listener = (event) => {
			var iron = this.shadowRoot.querySelector("iron-collapse-button");
			if (iron) iron.$.trigger.children[0].assignedNodes()[0].focus();
		};
		this.addEventListener('focus', listener);
		this.addEventListener('click', listener);
		this.addEventListener('mousedown', (event) => {
			if (event.button==1) {
				event.preventDefault();
				return false;
			}
		});
		this.addEventListener('mouseup', (event) => {
			event.preventDefault();
			return false;
		});
		this.addEventListener('contextmenu', event => {
			event.preventDefault();
		});
	}

	__hasIconChanged(hasIcon) {
		this.$.item.$.contentIcon.style.display=hasIcon?'flex':'none';
	}
	__hasIcon() {
		return !!(this.src || this.icon);
	}

	__closeDrawer() {
		let container = this.closest('[fc-menuitem-container]'); 
		if (container) container.close();
	}
	
	connectedCallback () {
		super.connectedCallback ();
		var slot = this.shadowRoot.querySelector("slot[name='menu-item']");
		var handler = this.__bindSubmenu.bind(this);
		slot.addEventListener('slotchange', handler);
		handler();
		this.__hasIconChanged(this.hasIcon);
	}
	
	__bindSubmenu() {
		var slot = this.shadowRoot.querySelector("slot[name='menu-item']");
		this.isSubmenu=slot && slot.assignedNodes().length>0;
		var iron = this.shadowRoot.querySelector("iron-collapse-button");
		if (this.isSubmenu) {
			iron.removeAttribute('no-icons');
		} else {
			iron.setAttribute('no-icons','true');
		}
	}
}

customElements.define(MenuItem.is, MenuItem);
