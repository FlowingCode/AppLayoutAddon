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
package com.flowingcode.addons.applayout.integration;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import com.vaadin.testbench.TestBenchElement;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ViewIT extends AbstractViewTest {

  private Matcher<TestBenchElement> hasBeenUpgradedToCustomElement =
      new TypeSafeDiagnosingMatcher<TestBenchElement>() {

        @Override
        public void describeTo(Description description) {
          description.appendText("a custom element");
        }

        @Override
        protected boolean matchesSafely(TestBenchElement item, Description mismatchDescription) {
          String script = "let s=arguments[0].shadowRoot; return !!(s&&s.childElementCount)";
          if (!item.getTagName().contains("-")) return true;
          if ((Boolean) item.getCommandExecutor().executeScript(script, item)) return true;
          else {
            mismatchDescription.appendText(item.getTagName() + " ");
            mismatchDescription.appendDescriptionOf(is(not(this)));
            return false;
          }
        }
      };

  @Test
  public void componentWorks() {
    TestBenchElement header = $("app-header").first();
    TestBenchElement drawer = $("app-drawer").first();

    assertThat(header, hasBeenUpgradedToCustomElement);
    assertThat(drawer, hasBeenUpgradedToCustomElement);

    TestBenchElement toolbar = header.findElement(By.tagName("app-toolbar"));
    assertThat(toolbar, hasBeenUpgradedToCustomElement);

    TestBenchElement menu = toolbar.findElement(By.cssSelector("paper-icon-button[icon='menu']"));
    toolbar.findElement(By.cssSelector("img.applogo"));
    toolbar.findElement(By.cssSelector("div[main-title]"));
    toolbar.findElement(By.cssSelector("paper-icon-button[role='button'][icon='settings']"));

    assertThat(menu, hasBeenUpgradedToCustomElement);

    TestBenchElement listbox = drawer.findElement(By.cssSelector("paper-listbox"));
    assertThat(listbox, hasBeenUpgradedToCustomElement);

    List<WebElement> items = listbox.findElements(By.cssSelector("*"));
    assertThat(items, is(not(empty())));

    for (WebElement item : items) {
      assertThat((TestBenchElement) item, hasBeenUpgradedToCustomElement);
    }
  }
}
