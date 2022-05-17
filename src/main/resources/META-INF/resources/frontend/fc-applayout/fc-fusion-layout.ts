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
import { CSSModule } from '@vaadin/flow-frontend/css-utils';
import { css, customElement, html, LitElement, state, property, query } from 'lit-element';
import "@flowingcode/fc-applayout";
import {FcAppLayoutElement} from "@flowingcode/fc-applayout/src/fc-applayout";
import "@flowingcode/fc-menuitem";
import * as menuItemEndpoint from '@vaadin/flow-frontend/generated/MenuEndpoint';
import MenuItemDto from '@vaadin/flow-frontend/generated/com/flowingcode/addons/applayout/endpoint/MenuItemDto';
import { EndpointError } from '@hilla/frontend';
import { Router } from '@vaadin/router';

@customElement('fc-fusion-layout')
export class FusionLayout extends LitElement {
  static get is() { return 'fc-fusion-layout'; }

  @query("#fusionLayout")
  fcAppLayout!: FcAppLayoutElement;

  @state()
  private menuItems: MenuItemDto[] = [];

  @property({type: String})
  title = "My Application";

  @property({type: String})
  profilePicture = "./images/user.svg";

  @property({type: String})
  profilePictureAlt = "Profile picture";

  @property({type: String})
  appLogo = "./icons/icon.png";

  @property({type: String})
  appLogoAlt = "Logo";

  @property({type: String})
  userName = "User";

  @property({type: Boolean})
  reveals = true;

  @property({type: Boolean})
  swipeOpen = true;

  @property({type: Boolean})
  fixed = false;

  @property({type: Router, attribute: false})
  router: Router | null = null;

  constructor() {
    super();
    this.addEventListener("menuitem-clicked-event", (e:Event) => {
      let fcmi: any = e.composedPath()[0];
      if (!fcmi.isSubmenu) {
        this.fcAppLayout.drawer.close();
      }
    });
  }

  static get styles() {
    return [
      CSSModule('lumo-typography'),
      CSSModule('lumo-color'),
      css`
        :host {
          display: block;
          height: 100%;
        }

        header {
          align-items: center;
          box-shadow: var(--lumo-box-shadow-s);
          display: flex;
          height: var(--lumo-size-xl);
          width: 100%;
        }

        header h1 {
          font-size: var(--lumo-font-size-l);
          margin: 0;
        }

        header img {
          border-radius: 50%;
          height: var(--lumo-size-s);
          margin-left: auto;
          margin-right: var(--lumo-space-m);
          overflow: hidden;
          background-color: var(--lumo-contrast);
        }

        #logo {
          align-items: center;
          box-sizing: border-box;
          display: flex;
          padding: var(--lumo-space-s) var(--lumo-space-m);
        }

        #logo img {
          height: calc(var(--lumo-size-l) * 1.5);
        }

        #logo span {
          font-size: var(--lumo-font-size-xl);
          font-weight: 600;
          margin: 0 var(--lumo-space-s);
        }

        .other {
          background-color: white;
		      color: var(--lumo-contrast);
        }
        .current {
          background-color: var(--lumo-contrast);
		      color: white;
        }
        .fusion-content-container {
          width: 100%;
          height: 100%;
        }
        .profile-title {
          width: 100%;
          display: block;
          font-size: large;
          font-weight: bold;
        }

      `,
    ];
  }

  render() {
    return html`
    <fc-applayout reveals=${this.reveals} swipeOpen=${this.swipeOpen} fixed=${this.fixed} id="fusionLayout">
      <div slot="profile" style="text-align: center;">
        <img src=${this.profilePicture} alt=${this.profilePictureAlt} style="width: 80px; margin-top: 20px;">
        <span class="profile-title" slot="profile">${this.userName}</span>
      </div>
      <img slot="title" class="applogo" src=${this.appLogo} alt=${this.appLogoAlt} style="width:50px">
      <div slot="title" main-title="">${this.title}</div>
      <slot name="toolbar" slot="toolbar"></slot>
      <div slot="menu" tabindex="0" aria-selected="false">
      ${this.menuItems.map(item => this.generateFcMenuItem(item))}
      </div>
      <div class="fusion-content-container">
        <slot></slot>
      </div>
    </fc-applayout>		
    `;
  }

  connectedCallback() {
    super.connectedCallback();
    this.buildMenu();
    window.addEventListener('requestRefreshMenu', 
      this.buildMenuEventListener,
      false
    );
    window.addEventListener("message", function(event) {
      if (event.data == 'redirect-welcome') {
        Router.go("welcome");
      }
    });
  }

  private buildMenuEventListener = () => {
    this.buildMenu();
  }

  disconnectedCallback() {
    super.disconnectedCallback();
    window.removeEventListener('requestRefreshMenu',
        this.buildMenuEventListener,
        false
    );
  }

  private currentLocationClass(route: string): string {
    return !route.startsWith("http://") && !route.startsWith("https://") && this.router!=null && this.router!.urlForPath(route) === this.router!.location.pathname?"current":"other";
  }

  private buildMenu(){
	window.requestAnimationFrame(() => this.updateMenuItems());
  }

  private async updateMenuItems(){
    await menuItemEndpoint.getMenuItems().then((mi: any[]) =>{
      this.menuItems = mi!
    } ).catch((error: { detail: any; message: any; })=>{
      if (error instanceof EndpointError) {
        console.error("EndpointError");
        console.error(error.detail);
        console.error(error.message);
      } else {
        console.error("Not EndpointError");
        console.error(error);
      }
    })
    }

  private generateFcMenuItem(item: MenuItemDto): any {
    let routerLink = item.href;
    let ret;
    // TODO: Add attrs: key, src, icon(if hasIcon), disabled, (isSubmenu?), onMenuItemClicked
    if(item.children.length>0) {
      ret = html`
          <fc-menuitem
            class="sub-menu"
            slot="menu-item" 
            label="${item.label}"
            >
            ${item.children.length>0 ? item.children.map((i: any) => this.generateFcMenuItem(i)) : ""}
          </fc-menuitem>
        `;
    } else {
      ret = html`
        <fc-menuitem 
          slot="menu-item" 
          class=${this.currentLocationClass(""+item.href)}
          href="${routerLink}"
          label="${item.label}"
		      @click=${this.buildMenu}
          >
        </fc-menuitem>
      `;
    }
    return ret;
  }
}
