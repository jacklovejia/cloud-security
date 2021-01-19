package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
/**
 * @EnableGlobalMethodSecurity详解
 * 1、@EnableGlobalMethodSecurity(securedEnabled=true) 开启@Secured 注解过滤权限
 *
 * 2、@EnableGlobalMethodSecurity(jsr250Enabled=true) 开启@RolesAllowed 注解过滤权限
 *
 * 3、@EnableGlobalMethodSecurity(prePostEnabled=true) 使用表达式时间方法级别的安全性
 *
 *     下面4个注解可用 就是放在 controller 接口上的 注解
 *
 *     @PreAuthorize 在方法调用之前, 基于表达式的计算结果来限制对方法的访问
 *
 *     @PostAuthorize 允许方法调用, 但是如果表达式计算结果为false, 将抛出一个安全性异常
 *
 *     @PostFilter 允许方法调用, 但必须按照表达式来过滤方法的结果
 *
 *     @PreFilter 允许方法调用, 但必须在进入方法之前过滤输入值
 *
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/orders/**").authenticated() // 所有/r/** 的请求必须认证通过
                .anyRequest().permitAll(); // 除了 /r/** 的 其他都可以访问

    }
}
