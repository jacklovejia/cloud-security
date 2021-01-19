package com.jack.oauth;

import java.lang.reflect.Proxy;
import java.time.LocalDateTime;

public class TestOauthApp {

    public static void main(String[] args) {
        UserService userService = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, new MyProxy(new UserServiceImpl()));
        String user = userService.getUser(1L);
        System.out.println(user);
        String a = "00";
        String b = "01";
        String user_type = a + "," + b; // 01 预付费 00 后付费
        System.out.println(user_type);


    }
}
