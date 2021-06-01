package com.jack.shiro.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jack.shiro.entity.Role;
import com.jack.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
