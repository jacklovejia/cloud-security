package com.jack.oauth;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Test {
    public static void main(String[] args) {
        String hashpw = BCrypt.hashpw("secret", BCrypt.gensalt());
        System.out.println(hashpw);
    }
}
