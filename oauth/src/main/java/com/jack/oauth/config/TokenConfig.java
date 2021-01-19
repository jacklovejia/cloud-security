package com.jack.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 令牌管理
 */
@Configuration
public class TokenConfig {

    // 签名字符串 资源服务也要放着秘钥, 这样资源服务就可以用这个解密了
    private String SIGNING_KEY = "jack1314";

    /**
     * 令牌存储策略 普通的
     *
     * @return
     */
//    @Bean
//    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }

    /**
     * jwt 令牌
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(AccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter AccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
}
