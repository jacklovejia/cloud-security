package com.jack.shiro.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jack.shiro.entity.Role;
import com.jack.shiro.entity.User;
import com.jack.shiro.mapper.RoleMapper;
import com.jack.shiro.mapper.UserMapper;
import com.jack.shiro.service.RoleService;
import com.jack.shiro.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
