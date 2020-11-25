package com.jd.em.base.config;

import com.jd.em.base.interceptors.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;


/**
 * @author KING
 *
 * <p>Description: interceptor</p>
 *
 * 2019年1月21日
 *
 */
@EnableWebMvc
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

	@Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/");
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
	}
	
 	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/plugins/**",  "/**/exclude/**", "/api/v1/**", "/visualtalk/call/app/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
 
 	@Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/index" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        WebMvcConfigurer.super.addViewControllers( registry );
    }
 	
 	/**
 	 * 
 	 *   跨域
 	 */
 	@Override
 	public void addCorsMappings(CorsRegistry registry) {
 		registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE");
 	}
 	
 	/**
 	 * 解决responsebody中文乱码
 	 */
 	@Override
 	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
 		WebMvcConfigurer.super.configureMessageConverters(converters);
 		converters.add(responseBodyConverter());
 	}
 	
 	@Bean
 	public HttpMessageConverter responseBodyConverter() {
 		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
 		return converter;
 	}
}
