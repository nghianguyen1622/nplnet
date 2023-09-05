package com.npl.global.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/fileupload/users/**").addResourceLocations("file:fileupload/users/");
		registry.addResourceHandler("/fileupload/product/**").addResourceLocations("file:fileupload/product/");
		registry.addResourceHandler("/fileupload/brand/**").addResourceLocations("file:fileupload/brand/");
		registry.addResourceHandler("/fileupload/category/**").addResourceLocations("file:fileupload/category/");
	}
}
