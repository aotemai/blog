package com.blog.inteceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInteceptor())
                .addPathPatterns("/admin/**")//过滤admin下面的路径
                .excludePathPatterns("/admin")//这个路径不需要过滤，所以排除
                .excludePathPatterns("/admin/login");//这个路径不需要过滤，所以排除
    }
}
