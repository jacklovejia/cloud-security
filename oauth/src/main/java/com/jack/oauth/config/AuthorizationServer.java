package com.jack.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 授权服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;


    @Autowired
    private AuthorizationCodeServices authorizationCodeServices; // 授权码模式

    @Autowired
    private AuthenticationManager authenticationManager; // 密码模式必须要有的  AuthenticationManager 认证管理器

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 配置有哪些 客户端 , 配置客户端详细信息, clientID, secret 秘钥 等等
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        super.configure(clients);
        // 这里从直接配置在内从中, 下面会从数据库中取
/*        clients.inMemory()
                .withClient("c1")// 客户端ID
                .secret(new BCryptPasswordEncoder().encode("secret")) // 客户端秘钥
                .resourceIds("res1")// 客户端可以访问的资源服务id
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("all") // 授权的范围, 读写啊, 什么权限啥的
                .autoApprove(false) // false 跳转到授权页面. true 不用跳转,直接发令牌
                .redirectUris("http://www.baidu.com"); // 加上验证回调地址*/
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * table: oauth_client_details
     * 资源服务配置在数据库中
     * @param dataSource 数据源
     * @return
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService)clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 令牌服务
     * 不管什么模式肯定都是需要的
     *
     * @return
     */
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService); // 客户端信息服务
        tokenServices.setReuseRefreshToken(true); // 是否产生刷新令牌
        tokenServices.setTokenStore(tokenStore);
        // 设置令牌增强 把jwt的配置加过来
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);

        tokenServices.setAccessTokenValiditySeconds(7200);
        tokenServices.setRefreshTokenValiditySeconds(259200);
        return tokenServices;
    }


    /**
     * 令牌暴露 访问端点
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)  // 密码模式需要的认证管理器
                .authorizationCodeServices(authorizationCodeServices) //授权码模式
                .tokenServices(tokenServices())// 令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); // post 提交访问令牌
    }

    /**
     * 配置令牌访问断点安全策略
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /*
         *  /oauth/authorize : 授权端点
         *  /oauth/token : 令牌端点
         *  /oauth/confirm_access : 用户确认授权提交端点
         *  /oauth/error : 授权服务错误信息端点
         *  /oauth/check_token : 用于资源服务访问的令牌解析端点
         *  /oauth/token_key : 提供公钥的端点，如果使用的是JWT令牌。
         *
         */
        security
                .tokenKeyAccess("permitAll()") // 公钥公开
                .checkTokenAccess("permitAll()") // 检测token公开
                .allowFormAuthenticationForClients(); // 表单认证
    }

    /**
     *  table: oauth_code
     *  无参数的话, 就是使用内存,
     *  有数据源的就是使用数据库的
     * @param dataSource
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
//        return new InMemoryAuthorizationCodeServices(); // 使用内存方式配置授权码
        return new JdbcAuthorizationCodeServices(dataSource); // 使用数据库存储授权码
    }

}
