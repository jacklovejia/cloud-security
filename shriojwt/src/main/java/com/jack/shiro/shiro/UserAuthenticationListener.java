package com.jack.shiro.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class UserAuthenticationListener implements AuthenticationListener {
    @Override
    public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
        log.info("用户{} 登录成功",info.getCredentials());
    }

    @Override
    public void onFailure(AuthenticationToken token, AuthenticationException ae) {
        log.info("用户{} 登录失败",token.getPrincipal());
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        log.info("用户{} 登录退出登录了我擦",principals.getPrimaryPrincipal());
    }
}
