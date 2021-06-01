package com.jack.shiro.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jack.shiro.entity.Function;
import com.jack.shiro.entity.User;
import com.jack.shiro.mapper.FunctionMapper;
import com.jack.shiro.mapper.UserMapper;
import com.jack.shiro.service.FunctionService;
import com.jack.shiro.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, Function> implements FunctionService {
}
