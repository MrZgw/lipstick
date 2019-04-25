package com.lipstick.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description
 * @Author Mint
 * @Date 2019/4/10 17:01
 **/
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/")
    public String hello() {
        return "Hello World!!!";
    }
}
