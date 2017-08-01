package com.hcl.smart.bank.operations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EntityScan("com.hcl.smart.bank.registration")
@ComponentScan(basePackages = "com.hcl.smart.bank")
public class SmartBankOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartBankOperationsApplication.class, args);
	}
	
	@Bean
	public Docket swaggerSettings() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/bank.*")).build().pathMapping("/");
	}
}
