package com.bridgeit.fundoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@AutoConfigurationPackage
@EnableEurekaClient
@EnableDiscoveryClient
public class FUndooNotesandLabelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FUndooNotesandLabelsApplication.class, args);
	}

}
