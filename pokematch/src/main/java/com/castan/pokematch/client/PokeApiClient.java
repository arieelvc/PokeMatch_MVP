package com.castan.pokematch.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.castan.pokematch.dto.PokeApiPokemonResponse;

@Component
public class PokeApiClient {

    private final WebClient pokeApiWebClient;

    public PokeApiClient(WebClient pokeApiWebClient) {
        this.pokeApiWebClient = pokeApiWebClient;
    }

    public PokeApiPokemonResponse getPokemon(String nameOrId) {

        try {
            return pokeApiWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("pokemon/{nameOrId}").build(nameOrId))
                    .retrieve()
                    .bodyToMono(PokeApiPokemonResponse.class)
                    .block();
        } catch (Exception e) {
            System.err.println("Pokemon Not Found:  " + e.getMessage());
            return null;
        }

    }
}
