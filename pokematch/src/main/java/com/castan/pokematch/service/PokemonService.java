package com.castan.pokematch.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.castan.pokematch.client.PokeApiClient;
import com.castan.pokematch.dto.PokemonDto;
import com.castan.pokematch.mapper.PokemonMapper;

@Service
public class PokemonService {

    private final PokeApiClient pokeApiClient;

    private final PokemonMapper pokemonMapper;

    public PokemonService(PokeApiClient pokeApiClient, PokemonMapper pokemonMapper) {
        this.pokeApiClient = pokeApiClient;
        this.pokemonMapper = pokemonMapper;
    }

    @Cacheable(value = "pokemons", unless = "#result == null")
    public PokemonDto getPokemonByNameOrId(String name) {
        var pokemon = pokeApiClient.getPokemon(name);
        if (pokemon == null) {
            return null;
        }
        return pokemonMapper.toDto(pokemon);
    }

}
