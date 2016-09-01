package com.ulife.cloud.demo

import com.weibo.api.motan.config.springsupport.ProtocolConfigBean
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ImportResource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import javax.annotation.Resource

/**
 * Created by Rocky on 8/23/16.
 */
@SpringBootApplication
//@EnableEurekaClient
@ImportResource(locations = "classpath:motan.xml")
@Controller
class DemoApplication {

    static void main(String[] args){
        System.setProperty("server.port","8091")
        def ctx = SpringApplication.run DemoApplication, args
//        def userService = ctx.getBean "userService",UserService
//        println userService.getUser("1")
    }




    @Bean(name = "motan")
    def protocolConfig1() {
        ProtocolConfigBean config = new ProtocolConfigBean();
        config.setDefault(true);
        config.setName("motan");
        config.setMaxContentLength(1048576);
        return config;
    }
}

