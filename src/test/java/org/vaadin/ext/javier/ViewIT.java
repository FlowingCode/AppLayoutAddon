package org.vaadin.ext.javier;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.vaadin.testbench.TestBenchElement;

public class ViewIT extends AbstractViewTest {

    @Test @Ignore
    public void componentWorks() {
        final TestBenchElement paperSlider = $("paper-slider").first();
        // Check that paper-slider contains at least one other element, which means that
        // is has been upgraded to a custom element and not just rendered as an empty
        // tag
        Assert.assertTrue(
                paperSlider.$(TestBenchElement.class).all().size() > 0);
    }
}
