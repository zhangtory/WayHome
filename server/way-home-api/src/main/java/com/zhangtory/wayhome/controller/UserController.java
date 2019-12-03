package com.zhangtory.wayhome.controller;

import com.zhangtory.wayhome.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user")
    public String addUser() {

        return "success";
    }



}
