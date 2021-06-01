package com.jack.shiro.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("role")
public class Role {

    @TableId
    private Integer id;
    private String name;
    private String desc;
}
