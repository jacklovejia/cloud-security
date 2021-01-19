package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 配置资源
 */
@Configuration
@EnableResourceServer
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURSE_ID = "res1";
    // 远程验证地址
    @Value("${remoteTokenServices:http://localhost:8080/oauth/check_token}")
    private String remoteTokenServicesCheckUrl;
    // 客户端id 验证时使用
    @Value("${clientId}")
    private String clientId;
    // 客户端秘钥 验证时使用
    @Value("${clientSecret}")
    private String clientSecret;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        /**
         * 令牌的验证有两种
         *  1. 如果资源服务和验证服务在同一个服务中, 那就可以使用 DefaultTokenServices 验证,
         *  2. 但是一般都是分开的, 要使用 RemoteTokenServices 来验证token, /oauth/check_token
         */
        resources.resourceId(RESOURSE_ID) // 资源ID
//                .tokenServices(remoteTokenServices()) // 远程验证令牌的服务
                .tokenStore(tokenStore)
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and().csrf().disable() // 关闭跨域
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 不用记录session, 因为我们是用token


    }

    /**
     * 配置远程验证的token 服务
     *
     * @return
     */
/*    @Bean
    public ResourceServerTokenServices remoteTokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(remoteTokenServicesCheckUrl);
        remoteTokenServices.setClientId(clientId);
        remoteTokenServices.setClientSecret(clientSecret);
        return remoteTokenServices;
    }*/


}
