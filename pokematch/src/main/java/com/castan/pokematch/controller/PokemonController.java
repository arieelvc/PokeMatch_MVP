package com.castan.pokematch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castan.pokematch.dto.PokemonDto;
import com.castan.pokematch.service.PokemonService;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {

    final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{nameOrId}")
    public ResponseEntity<PokemonDto> getPokemon(@PathVariable String nameOrId) {
        PokemonDto pokemon = pokemonService.getPokemonByNameOrId(nameOrId);
        if (pokemon == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pokemon);
    }

}
