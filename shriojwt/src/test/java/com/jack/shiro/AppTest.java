package com.jack.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jack.shiro.entity.User;
import com.jack.shiro.mapper.UserMapper;
import com.jack.shiro.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest

public class AppTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Test
    public void t() throws IOException {
        Process p = Runtime.getRuntime().exec("mvn -v /");
        InputStream in = p.getInputStream();
        StringBuffer sb = new StringBuffer();

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        String str = sb.toString();
        System.out.println(sb.toString()); new EntityWrapper<>();
//        List<User> users = userService.selectList(x);
//        System.out.println(users);
        String s = new Md5Hash("123456").toString();
        System.out.println(s);
    }


    public static void main(String[] args) throws IOException {
        //将根目录下的文件列出并将结果写入 /tmp/list.out

    }

}
