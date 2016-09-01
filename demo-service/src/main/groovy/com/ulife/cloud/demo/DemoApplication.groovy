package com.ulife.cloud.demo

import com.weibo.api.motan.common.MotanConstants
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean
import com.weibo.api.motan.util.MotanSwitcherUtil
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ImportResource

/**
 * Created by Rocky on 8/23/16.
 */
@SpringBootApplication
//@EnableEurekaClient
@ImportResource(locations = "classpath:motan.xml")
class DemoApplication {

    static void main(String[] args){
        SpringApplication.run DemoApplication, args
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true)
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

