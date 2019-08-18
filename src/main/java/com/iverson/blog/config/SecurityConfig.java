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

    private static  final String KEY = "SECURITY_SYSTEM";

    /**
     * 拦截配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/css/**","/js/**","/fonts/**","/index").permitAll()//均可访问
            .antMatchers("/h2-console/**").permitAll()//均可访问
            .antMatchers("/admins/**").hasRole("admin")//需要相应的角色才可以访问
            .and()
            .formLogin()//基于form表单登陆验证
            .loginPage("/login").failureUrl("/login-error")
            .and().rememberMe().key(KEY)//启用RememberMe模式
            .and().exceptionHandling().accessDeniedPage("/403");//处理异常，拒绝访问就重定向到403页面
        http.csrf().ignoringAntMatchers("/h2-console/**");//禁用H2控制台的CSRF防护
        http.headers().frameOptions().sameOrigin();//允许来自同一来源的H2控制的请求
    }

    
}
