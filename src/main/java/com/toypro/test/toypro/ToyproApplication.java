package com.toypro.test.toypro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ToyproApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToyproApplication.class, args);
	}
	
}
