package com.tenwell.smalltalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@EnableReactiveMongoAuditing
@SpringBootApplication
public class SmalltalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmalltalkApplication.class, args);
	}

}