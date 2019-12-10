import {PolymerElement} from '@polymer/polymer/polymer-element.js';
import {html} from '@polymer/polymer/lib/utils/html-tag.js';

import {ThemableMixin} from '@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js';

/*
Styles: 
	--fc-separator-border-color	
	--fc-separator-text-padding
 */
class MenuSeparator extends ThemableMixin(PolymerElement) {
	static get is() { return 'fc-separator'; }
	
	static get template() {
		//Based on https://stackoverflow.com/a/28434792/1297272
		//(C) 2015 CC-BY-SA https://stackoverflow.com/users/677418/sleek-geek
		return html`
		<style>
			:host {
			    display: flex;
    			justify-content: center;
    			opacity: 0.6;
    		}
			:host div {
				width: 100%;
				text-align: center;
				position: relative;
			}
			
			:host div:after {
				content: '';
				width: 100%;
				position: absolute;
				border-bottom: solid 1px var(--fc-separator-border-color, rgba(0,0,0, 0.6));
				left: 0;
				top: 50%;
				z-index: 1;
			}
			
			:host span {
				width: auto;
				display: inline-block;
				z-index: 3;
				padding: 0 var(--fc-separator-text-padding, 8px) 0 var(--fc-separator-text-padding, 8px);
				position: relative;
			}
		</style>
		<div>
			<span><slot></slot></span>
		</div>
	`;}

}	
	
customElements.define(MenuSeparator.is, MenuSeparator);