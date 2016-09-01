package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class SpringCloudApplication {

	static void main(String[] args) {
		SpringApplication.run SpringCloudApplication, args
	}
}
