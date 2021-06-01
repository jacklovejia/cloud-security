package com.jack.shiro.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("user")
public class User {

    @TableId
    private Integer id;
    private String name;
    private String password;
}
