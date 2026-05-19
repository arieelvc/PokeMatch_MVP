package com.castan.pokematch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient pokeApiWebClient() {
        // Configuramos una estrategia de intercambio con un límite de 10 MB (10 * 1024
        // * 1024)
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();
        return WebClient.builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .exchangeStrategies(strategies) // Le aplicamos la estrategia al constructor
                .build();
    }

}
