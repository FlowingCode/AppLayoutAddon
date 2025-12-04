package com.flowingcode.vaadin.addons.applayout;

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.flowingcode.vaadin.addons.demo.TabbedDemo;
import com.flowingcode.vaadin.addons.demo.ThemeChangeObserver;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@DemoSource("/src/test/java/com/flowingcode/addons/applayout/CustomAppLayout.java")
@DemoSource("/src/test/java/com/flowingcode/addons/applayout/SampleView.java")
@PageTitle("Extending AppLayout Demo")
@SuppressWarnings("serial")
@Route(value = "applayout/extending-applayout-demo", layout = AppLayoutDemoView.class)
public class ExtendingAppLayoutDemo extends Div implements ThemeChangeObserver {

  private IFrame iframe;

  public ExtendingAppLayoutDemo() {
    setClassName("wrap-iframe");
    String themeName = (String) VaadinSession.getCurrent()
        .getAttribute(TabbedDemo.class.getName() + "#THEME_NAME");
    iframe = new IFrame("/view" + (themeName == "dark" ? "?theme=dark" : ""));
    iframe.setClassName("frame");
    iframe.setSizeFull();
    iframe.getElement().setAttribute("frameBorder", "0");
    add(iframe);
  }

  @Override
  public void onThemeChange(String themeName) {
    iframe.setSrc("/view" + (themeName == "dark" ? "?theme=dark" : ""));
  }
}
