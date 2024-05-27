package com.web.ddajait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication()
public class DdajaitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdajaitApplication.class, args);
	}

}
