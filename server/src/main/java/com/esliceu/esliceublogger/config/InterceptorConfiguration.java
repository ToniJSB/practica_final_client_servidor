/**
 * @author: Alexis Mora
 */
package com.esliceu.esliceublogger.config;

import com.esliceu.esliceublogger.handler.TokenHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Bean
    public TokenHandler getHandler() {
        return new TokenHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenHandler());
        registry.addInterceptor(getHandler()).addPathPatterns("/**").excludePathPatterns("");
    }
}