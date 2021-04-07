package com.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @link WebPathInterceptor
 */

@Configuration
public class WebPathInterceptor implements WebMvcConfigurer {

    /**
     * 配置在拦截之前加载，导致拦截里面无法正常注入，所以提前初始化一下拦截器
     *
     */
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
