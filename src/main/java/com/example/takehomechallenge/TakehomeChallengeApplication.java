package com.example.takehomechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class TakehomeChallengeApplication {

	public static void main(String[] args) {

		SpringApplication.run(TakehomeChallengeApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.example.takehomechallenge"))
				.build()
				.apiInfo(getApiDetails());
	}

	private ApiInfo getApiDetails() {
		return new ApiInfo(
				"City Route API",
				"API that determines if two cities can be connected by roads",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Dipak Ghosh", "http://browny.com", "gajanan108@otmail.com"),
				"API License",
				"http://browny.com",
				Collections.emptyList()
		);
	}
}
