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
package com.flowingcode.addons.applayout.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;

import com.flowingcode.addons.applayout.endpoint.MenuEndpoint;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

import dev.hilla.Endpoint;
import dev.hilla.EndpointRegistry;

@SuppressWarnings("serial")
public class RegisterEndpointServiceInitListener implements VaadinServiceInitListener{

    private ApplicationContext context;
    
    private transient EndpointRegistry endpointRegistry;

    public RegisterEndpointServiceInitListener(EndpointRegistry endpointRegistry2, ApplicationContext context) {
		this.endpointRegistry = endpointRegistry2;
		this.context = context;
	}

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
            throw new IllegalStateException("Problem registering endpoint",e);
        }
    }
  
}
