package com.castan.pokematch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castan.pokematch.dto.BattleResultDto;
import com.castan.pokematch.dto.PokemonDto;
import com.castan.pokematch.service.BattleSimulatorService;
import com.castan.pokematch.service.PokemonService;

@RestController
@RequestMapping("/api/v1/battle")
public class BattleController {

    private final PokemonService pokemonService;
    private final BattleSimulatorService battleSimulatorService;

    // Inyección limpia por constructor de ambos servicios
    public BattleController(PokemonService pokemonService, BattleSimulatorService battleSimulatorService) {
        this.pokemonService = pokemonService;
        this.battleSimulatorService = battleSimulatorService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<BattleResultDto> simulateBattle(@RequestBody BattleRequest request) {
        // 1. Buscamos ambos Pokémon en la PokeAPI a través de nuestro servicio
        PokemonDto player = pokemonService.getPokemonByNameOrId(request.pokemonA());
        PokemonDto opponent = pokemonService.getPokemonByNameOrId(request.pokemonB());

        // 2. Validación defensiva: Si uno no existe, devolvemos un HTTP 400 Bad Request
        if (player == null || opponent == null) {
            return ResponseEntity.badRequest().build();
        }

        // 3. Ejecutamos la simulación del combate
        BattleResultDto result = battleSimulatorService.simulateBattle(player, opponent);

        // 4. Devolvemos el reporte de la batalla en JSON con un HTTP 200 OK
        return ResponseEntity.ok(result);
    }

    // Record interno de soporte para mapear el JSON de entrada limpio
    public record BattleRequest(String pokemonA, String pokemonB) {
    }
}
