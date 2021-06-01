package com.jack.shiro.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jack.shiro.entity.Role;
import com.jack.shiro.entity.RoleFunction;
import com.jack.shiro.mapper.RoleFunctionMapper;
import com.jack.shiro.mapper.RoleMapper;
import com.jack.shiro.service.RoleFunctionService;
import com.jack.shiro.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleFunctionServiceImpl extends ServiceImpl<RoleFunctionMapper, RoleFunction> implements RoleFunctionService {
}
