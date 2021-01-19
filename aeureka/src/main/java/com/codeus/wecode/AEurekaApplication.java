package com.codeus.wecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 启动类
 * @author 大仙
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class AEurekaApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AEurekaApplication.class, args);
	}


}
