package com.prliexpress.gateway.servicioconecta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableConfigurationProperties
@EnableFeignClients
@SpringBootApplication
@EnableScheduling
	public class ServicioconectaApplication  extends SpringBootServletInitializer{

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServicioconectaApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ServicioconectaApplication.class, args);
	}

}



































