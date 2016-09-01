package com.ulife.cloud.demo

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource

/**
 * Created by Rocky on 8/29/16.
 */
@RestController
class DemoController {

    @Resource UserService userService;

    @RequestMapping("test")
    def test(){
        return userService.getUser("1")
    }

    @RequestMapping("test2")
    def test2(){
        return userService.getUsers()
    }
}
