import {PolymerElement} from '@polymer/polymer/polymer-element.js';
import {html} from '@polymer/polymer/lib/utils/html-tag.js';

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
			:host(:not([has-icon])) {
				--paper-item-icon-width: 0;
			}		
			:host(.iron-selected) > iron-collapse-button > paper-icon-item {
				font-weight: var(--paper-item-selected-weight, bold);
			}
			:host paper-icon-item {
				width: 100%;
			}
			:host iron-collapse-button {
				background: inherit;
			}
		</style>
		
		<iron-collapse-button no-icons="true">
			<paper-icon-item slot="collapse-trigger" role="option" disabled="[[disabled]]">
				<iron-icon src="[[image]]" icon="[[icon]]" slot="item-icon"></iron-icon>
				<span>[[label]]</span>
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
			icon  : String,
			image : String,
			disabled: Boolean,
			hasIcon: {
				type: Boolean,
				reflectToAttribute: true,
				computed: '__hasIcon(src,icon)'
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
			this.dispatchEvent(new CustomEvent('item-click', {bubbles: true, detail: event.button}));
			event.preventDefault();
			return false;
		});
		this.addEventListener('contextmenu', event => {
			event.preventDefault();
		});
	}
	
	__hasIcon() {
		return !!(this.src || this.icon);
	}
		
	connectedCallback () {
		super.connectedCallback ();
		var slot = this.shadowRoot.querySelector("slot[name='menu-item']");
		var handler = this.__bindSubmenu.bind(this);	
		slot.addEventListener('slotchange', handler);
		setTimeout(handler);
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