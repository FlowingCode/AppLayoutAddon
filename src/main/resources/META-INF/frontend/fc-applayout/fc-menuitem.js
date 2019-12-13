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
		</style>
	         	
	    <iron-iconset-svg name="fc-menuitem-icons" size="24">
			<svg><defs>
			<g id="empty"></g>
			</defs></svg>
		</iron-iconset-svg>
	
		<iron-collapse-button no-icons="true">
			<paper-icon-item id="item" slot="collapse-trigger" role="option" disabled="[[disabled]]">
				<iron-icon src="[[src]]" icon="[[icon]]" slot="item-icon"></iron-icon>
				<span id="label">[[label]]</span>
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