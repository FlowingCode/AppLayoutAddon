package com.flowingcode.vaadin.addons.applayout;

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@DemoSource("/src/test/java/com/flowingcode/addons/applayout/ApplayoutDemoView.java")
@DemoSource("/frontend/styles/app-layout/demo-styles.css")
@PageTitle("AppLayout Full Demo")
@SuppressWarnings("serial")
@Route(value = "applayout/applayout-demo", layout = AppLayoutDemoView.class)
public class AppLayoutDemo extends Div {

  public AppLayoutDemo() {
    setClassName("wrap-iframe");
    IFrame iframe = new IFrame("/applayout-full");
    iframe.setClassName("frame");
    iframe.setSizeFull();
    iframe.getElement().setAttribute("frameBorder", "0");
    add(iframe);
  }
}
