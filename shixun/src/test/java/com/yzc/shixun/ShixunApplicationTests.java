package com.yzc.shixun;

import com.yzc.shixun.dto.CustInfo;
import com.yzc.shixun.dto.UserInfo;
import com.yzc.shixun.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShixunApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        UserInfo userInfo=new UserInfo(-1,"1","haokan","1","顾客","123123212332@qq.com");
        CustInfo custInfo=new CustInfo(-1,"yyyy","123345678905","喜欢辣的","行健",1);
        userService.insertUserInfoCust(userInfo,custInfo);
    }

}
