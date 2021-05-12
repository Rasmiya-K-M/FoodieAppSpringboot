package com.stackroute.GatewayService;

import com.stackroute.GatewayService.filter.ErrorFilter;
import com.stackroute.GatewayService.filter.PostFilter;
import com.stackroute.GatewayService.filter.PreFilter;
import com.stackroute.GatewayService.filter.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
	@Bean
	public PreFilter preFilter(){
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter(){
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter(){
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter(){
		return  new RouteFilter();
	}
}