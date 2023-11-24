package com.sarahmartin.recettes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.sarahmartin.recettes")
@EntityScan("com.sarahmartin.recettes")
@SpringBootApplication
public class RecettesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecettesApplication.class, args);
	}

}
