package com.jack.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        String encode = passwordEncoder().encode("1314");
        manager.createUser(User.withUsername("jack").password(encode).authorities("p1").build());
        return manager;
    }
    /**
     * 密码加密策略
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/r/**").authenticated() // 所有/r/** 的请求必须认证通过
                .anyRequest().permitAll() // 除了 /r/** 的 其他都可以访问
                .and()
                .formLogin() // 允许表单登陆
//                .loginPage("/login-view") //登陆页面
//                .loginProcessingUrl("/login")
//                .successForwardUrl("/login-success")// 自定义登陆成功页面地址
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-view?logout");

    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .httpBasic()
//                .authenticationEntryPoint(authenticationEntryPoint)
//
//                .and()
//                .authorizeRequests()
//                // 所有/权限认证 的所有请求 都放行
//                .antMatchers("/user/**").permitAll()
//                .antMatchers("/auth/**").permitAll()
//                // swagger start
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/images/**").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/v2/api-docs").permitAll()
//
//                .anyRequest()
//                .authenticated()// 其他 url 需要身份认证
//
//                .and()
//                .formLogin()  // 开启登录
//                .loginProcessingUrl("/user/login")
//                .usernameParameter("username")//请求验证参数
//                .passwordParameter("password")//请求验证参数
//                .successHandler(authenticationSuccessHandler) // 登录成功
//                .failureHandler(authenticationFailureHandler) // 登录失败
//                .permitAll()
//
//                .and()
//                .logout()
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .permitAll();
//
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问
//
//    }

    /**
     * 认证管理器
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
