package com.flowingcode.vaadin.addons.applayout;

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource("/src/test/java/com/flowingcode/addons/applayout/CustomAppLayout.java")
@DemoSource("/src/test/java/com/flowingcode/addons/applayout/SampleView.java")
@PageTitle("Extending AppLayout Demo")
@SuppressWarnings("serial")
@Route(value = "applayout/extending-applayout-demo", layout = AppLayoutDemoView.class)
public class ExtendingAppLayoutDemo extends Div {

  public ExtendingAppLayoutDemo() {
    setClassName("wrap-iframe");
    IFrame iframe = new IFrame("/view");
    iframe.setClassName("frame");
    iframe.setSizeFull();
    iframe.getElement().setAttribute("frameBorder", "0");
    add(iframe);
  }
}
