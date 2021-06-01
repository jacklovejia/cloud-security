package com.jack.shiro.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("user_role")
public class UserRole {

    @TableId
    private Integer id;
    private Integer userId;
    private Integer roleId;
}
