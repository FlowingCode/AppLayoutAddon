package com.flowingcode.addons.applayout.integration;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.testbench.TestBenchElement;

public class ViewIT extends AbstractViewTest {

	private Matcher<TestBenchElement> hasBeenUpgradedToCustomElement = new TypeSafeDiagnosingMatcher<TestBenchElement>() {

		@Override
		public void describeTo(Description description) {
			description.appendText("a custom element");
		}
		
		@Override
		protected boolean matchesSafely(TestBenchElement item, Description mismatchDescription) {
			String script = "let s=arguments[0].shadowRoot; return !!(s&&s.childElementCount)";
			if (!item.getTagName().contains("-"))
				return true;
			if ((Boolean)item.getCommandExecutor().executeScript(script, item)) 
				return true;
			else {				
				mismatchDescription.appendText(item.getTagName()+" ");
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
    		assertThat((TestBenchElement)item, hasBeenUpgradedToCustomElement);
    	}
    }
    
}
