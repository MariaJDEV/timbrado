package com.perliexpress.back.timbrado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableConfigurationProperties
@EnableFeignClients
@SpringBootApplication
public class ComPerliexpressBackTimbradoApplication  extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ComPerliexpressBackTimbradoApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ComPerliexpressBackTimbradoApplication.class, args);
	}

}
