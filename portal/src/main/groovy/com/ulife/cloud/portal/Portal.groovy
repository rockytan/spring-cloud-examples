package com.ulife.cloud.portal

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by Rocky on 8/23/16.
 */
@SpringBootApplication
class Portal {

    static void main(String[] args){
        System.setProperty("server.port","9090")
        SpringApplication.run Portal,args
    }
}
