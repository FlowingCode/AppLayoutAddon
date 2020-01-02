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



public class DemoSettings {

	private boolean enabled;
	
	private boolean menuVisible;
	
	private boolean swipeOpen;
	
	private boolean fixed;
	
	private boolean reveals;
	
	private boolean compact;

	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isMenuVisible() {
		return menuVisible;
	}

	public void setMenuVisible(boolean menuVisible) {
		this.menuVisible = menuVisible;
	}

	public boolean isSwipeOpen() {
		return swipeOpen;
	}

	public void setSwipeOpen(boolean swipeOpen) {
		this.swipeOpen = swipeOpen;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public boolean isReveals() {
		return reveals;
	}

	public void setReveals(boolean reveals) {
		this.reveals = reveals;
	}

	public boolean isCompact() {
		return compact;
	}

	public void setCompact(boolean compact) {
		this.compact = compact;
	}
	
}
