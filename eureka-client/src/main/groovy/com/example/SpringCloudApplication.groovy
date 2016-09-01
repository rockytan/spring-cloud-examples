package com.example

import com.netflix.discovery.DiscoveryClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaClient
@RestController
class SpringCloudApplication {

	@Autowired
	private DiscoveryClient discoveryClient;

	static void main(String[] args) {
		SpringApplication.run SpringCloudApplication, args
	}

	@RequestMapping("/view")
	def view(){
		//discoveryClient.services.forEach { println it }

		//discoveryClient.getInstances "demo-service" forEach { println it.host;println it.uri }
		def url = discoveryClient.getNextServerFromEureka "demo-service",true homePageUrl
		println url

		//discoveryClient.getne

		"success"
	}
}
