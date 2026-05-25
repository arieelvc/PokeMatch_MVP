package com.castan.pokematch.dto;

import java.util.List;

public record PokeApiPokemonResponse(
                Long id,
                String name,
                List<TypeSlot> types,
                List<StatSlot> stats,
                Sprites sprites) {
        // Mapea la estructura: "types": [ { "slot": 1, "type": { "name": "electric",
        // "url": "..." } } ]
        public record TypeSlot(
                        int slot,
                        TypeDetail type) {
        }

        public record TypeDetail(
                        String name,
                        String url) {
        }

        // Mapea la estructura: "stats": [ { "base_stat": 35, "effort": 0, "stat": {
        // "name": "hp", "url": "..." } } ]
        public record StatSlot(
                        int base_stat,
                        int effort,
                        StatDetail stat) {
        }

        public record StatDetail(
                        String name,
                        String url) {
        }

        // Mapea la estructura: "sprites": { "front_default": "url...", "front_shiny":
        // "url..." }
        public record Sprites(
                        String front_default,
                        String front_shiny) {
        }

}
