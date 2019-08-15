package com.iverson.blog.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Description: 安全配置类
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/15
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 拦截配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/css/**","/js/**","/fonts/**","/index").permitAll()//均可访问
            .antMatchers("/users/**").hasRole("admin")//需要相应的角色才可以访问
            .and()
            .formLogin()//基于form表单登陆验证
            .loginPage("/login").failureUrl("/login-error");//跳转登录页面
    }
}
