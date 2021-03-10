package com.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebPathInterceptor implements WebMvcConfigurer {

    @Bean
    public AllInterceptor getAllInterceptor() {
        return new AllInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAllInterceptor())
                .addPathPatterns("/**");
    }
}
