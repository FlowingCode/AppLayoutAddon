/**
@license MIT

Copyright (c) 2017 Jacob Phillips

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
import '@polymer/polymer/polymer-legacy.js';

import '@polymer/iron-flex-layout/iron-flex-layout.js';
import '@polymer/iron-iconset-svg/iron-iconset-svg.js';
import '@polymer/iron-icon/iron-icon.js';
import '@polymer/iron-collapse/iron-collapse.js';

import {Polymer} from '@polymer/polymer/lib/legacy/polymer-fn.js';
import {html} from '@polymer/polymer/lib/utils/html-tag.js';

Polymer({
  _template: html`
    <style>
      :host {
        display: block;
      }
      #trigger {
        @apply --layout-horizontal;
        @apply --layout-center;
      }
    </style>

	<iron-iconset-svg name="iron-collapse-button-icons" size="24">
	<svg><defs>
	<g id="expand-less"><path d="M12 8l-6 6 1.41 1.41L12 10.83l4.59 4.58L18 14z"/></g>
	<g id="expand-more"><path d="M16.59 8.59L12 13.17 7.41 8.59 6 10l6 6 6-6z"/></g>
	</defs></svg>
	</iron-iconset-svg>

    <div id="trigger" on-tap="toggle">
      <slot name="collapse-trigger"></slot>
      <iron-icon icon="[[_toggle(opened, collapseIcon, expandIcon)]]" hidden$="[[noIcons]]"></iron-icon>
    </div>
    <iron-collapse id="collapse" horizontal="[[horizontal]]" no-animation="[[noAnimation]]" opened="[[opened]]">
      <slot name="collapse-content"></slot>
    </iron-collapse>
`,

  is: 'iron-collapse-button',
  properties: {
    /**
      * @deprecated
      * Corresponds to the iron-collapse's horizontal property.
      */
    horizontal: {
      type: Boolean,
      value: false
    },
    /**
      * Corresponds to the iron-collapse's noAnimation property.
      */
    noAnimation: {
      type: Boolean,
      value: false
    },
    /**
      * Whether currently expanded.
      */
    opened: {
      type: Boolean,
      value: false,
      notify: true
    },
    /**
      * The icon when collapsed.
      */
    expandIcon: {
      type: String,
      value: 'iron-collapse-button-icons:expand-more'
    },
    /**
      * The icon when expanded.
      */
    collapseIcon: {
      type: String,
      value: 'iron-collapse-button-icons:expand-less'
    },
    /**
      * Whether to hide the expand/collapse icon.
      */
    noIcons: {
      type: Boolean,
      value: false
    },
  },
  /** @depricated Use `open()`. */
  show: function() { this.open(); },
  /** @depricated Use `close()`. */
  hide: function() { this.close(); },
  open: function() { this.opened = true; },
  close: function() { this.opened = false; },
  toggle: function() { this.opened = !this.opened; },
  _toggle: function(cond, t, f) { return cond ? t : f; }
});