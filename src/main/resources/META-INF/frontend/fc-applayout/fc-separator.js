import {PolymerElement} from '@polymer/polymer/polymer-element.js';
import {html} from '@polymer/polymer/lib/utils/html-tag.js';

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