package com.jack.shiro.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("role_function")
public class RoleFunction {

    @TableId
    private Integer id;
    private Integer roleId;
    private Integer functionId;
}
