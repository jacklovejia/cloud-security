package com.jack.oauth;

import java.util.Objects;

public class UserServiceImpl implements UserService {
    @Override
    public String getUser(Long id) {
        return "user" + id;
    }

    @Override
    public String getUser1(Long id) {
        return null;
    }
}
