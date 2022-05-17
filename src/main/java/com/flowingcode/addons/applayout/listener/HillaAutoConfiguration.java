package com.flowingcode.addons.applayout.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.flowingcode.addons.applayout.endpoint.MenuEndpoint;
import com.flowingcode.addons.applayout.endpoint.MenuItemsProvider;

import dev.hilla.EndpointControllerConfiguration;
import dev.hilla.EndpointRegistry;

@Configuration
@ConditionalOnClass(value = EndpointControllerConfiguration.class)
public class HillaAutoConfiguration {

	@Bean
	public RegisterEndpointServiceInitListener registerEndpointServiceInitListener(
			@Autowired EndpointRegistry endpointRegistry, @Autowired ApplicationContext applicationContext) {
		return new RegisterEndpointServiceInitListener(endpointRegistry, applicationContext);
	}
	
	@Bean MenuEndpoint menuEndpoint(List<MenuItemsProvider> menuItemsProviders) {
		return new MenuEndpoint(menuItemsProviders);
	}
	
}
