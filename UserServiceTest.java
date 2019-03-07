package com.user.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.user.bean.User;
import com.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml","classpath:mybatis-config.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        fail("Not yet implemented");
        User user = new User();
        user.setId("1");
        user.setLoginname("aa");
        user.setPassword("123");
        userService.addUser(user);
    }

}