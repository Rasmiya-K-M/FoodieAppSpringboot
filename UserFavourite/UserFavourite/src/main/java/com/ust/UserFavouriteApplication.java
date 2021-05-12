package com.ust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class UserFavouriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserFavouriteApplication.class, args);
	}

}
