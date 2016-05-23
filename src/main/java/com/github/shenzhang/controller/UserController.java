package com.github.shenzhang.controller;

import com.github.shenzhang.dto.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Zhang Shen on 5/23/16.
 */
@RestController("/users")
public class UserController {
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        List<User> list = newArrayList();
        list.add(new User(1, "zhangsan"));
        list.add(new User(2, "lisi"));
        return list;
    }
}
