package com.castan.pokematch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PokematchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokematchApplication.class, args);
	}

}
