package com.castan.pokematch.dto;

import java.util.List;

import lombok.Data;

@Data
public class PokemonDto {

    private Long id;
    private String name;
    private List<String> types;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private String imageUrl;
}
