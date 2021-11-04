package com.flowingcode.addons.applayout.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.flowingcode.addons.applayout.endpoint.MenuEndpoint;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.fusion.Endpoint;
import com.vaadin.fusion.EndpointRegistry;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class RegisterEndpointServiceInitListener implements VaadinServiceInitListener, ApplicationContextAware {

    private static ApplicationContext context;
    
    private EndpointRegistry endpointRegistry;

    @Override
    public void serviceInit(ServiceInitEvent event) {
        endpointRegistry = context.getBean(EndpointRegistry.class);
        context.getBeansWithAnnotation(Endpoint.class)
                .forEach((name, endpointBean) -> {
                    if (endpointBean instanceof MenuEndpoint) {
                        registerEndpoint(endpointBean);
                    }
                });
    }

    private void registerEndpoint(Object endpointBean) {
        try {
            Method m = EndpointRegistry.class.getDeclaredMethod("registerEndpoint", Object.class);
            m.setAccessible(true);
            m.invoke(endpointRegistry, endpointBean);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException("Problem registering endpoint",e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    
}
