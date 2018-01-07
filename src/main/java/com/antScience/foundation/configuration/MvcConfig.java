package com.antScience.foundation.configuration;

import com.antScience.foundation.filter.AuthorityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lirui on 2017/4/27.
 */

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        AuthorityFilter authorityFilter = new AuthorityFilter();
        registrationBean.setFilter(authorityFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }

//    @ApiOperation("注入登录拦截器")
//    @Bean
//    public LoginInterceptor loginInterceptor() {
//        return new LoginInterceptor();
//    }

//   @ApiOperation("配置拦截器，确定拦截规则和排除拦截规则")
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//         registry.addInterceptor(loginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/error")
//                .excludePathPatterns("/auth/**");
//     }

//    @Override
    //    public void addCorsMappings(CorsRegistry registry) {
    //        registry
    //                .addMapping("/**")
    //                .allowedOrigins("*")
    //                .allowedHeaders("token", "userName")
    //                .exposedHeaders("token", "userName")
    //                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
    //                .allowCredentials(false).maxAge(3600);
    //     }

}