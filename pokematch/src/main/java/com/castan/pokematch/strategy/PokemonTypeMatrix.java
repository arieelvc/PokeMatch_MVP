package com.castan.pokematch.strategy;

import java.util.EnumMap;
import java.util.Map;

import com.castan.pokematch.dto.PokemonType;

public class PokemonTypeMatrix {
    private static final Map<PokemonType, Map<PokemonType, Double>> MATRIX = new EnumMap<>(
            PokemonType.class);

    // 2. Inicializamos la matriz de datos en un bloque estático
    static {
        // --- Reglas para el tipo FUEGO ---
        Map<PokemonType, Double> fireRules = new EnumMap<>(PokemonType.class);
        fireRules.put(PokemonType.PLANT, 2.0); // Fuego es súper efectivo contra Planta
        fireRules.put(PokemonType.WATER, 0.5); // Fuego hace la mitad de daño a Agua
        fireRules.put(PokemonType.FIRE, 0.5); // Fuego hace la mitad de daño a Fuego
        MATRIX.put(PokemonType.FIRE, fireRules);

        // --- Reglas para el tipo AGUA ---
        Map<PokemonType, Double> waterRules = new EnumMap<>(PokemonType.class);
        waterRules.put(PokemonType.FIRE, 2.0);
        waterRules.put(PokemonType.PLANT, 0.5);
        waterRules.put(PokemonType.WATER, 0.5);
        MATRIX.put(PokemonType.WATER, waterRules);

        // --- Reglas para el tipo PLANTA ---
        Map<PokemonType, Double> plantRules = new EnumMap<>(PokemonType.class);
        plantRules.put(PokemonType.WATER, 2.0);
        plantRules.put(PokemonType.FIRE, 0.5);
        plantRules.put(PokemonType.PLANT, 0.5);
        MATRIX.put(PokemonType.PLANT, plantRules);

        // --- Reglas para el tipo ELÉCTRICO ---
        Map<PokemonType, Double> electricRules = new EnumMap<>(PokemonType.class);
        electricRules.put(PokemonType.WATER, 2.0);
        electricRules.put(PokemonType.PLANT, 0.5);
        MATRIX.put(PokemonType.ELECTRIC, electricRules);
    }

    private PokemonTypeMatrix() {
    }

    public static Map<PokemonType, Map<PokemonType, Double>> getEffectivenessMap() {
        return MATRIX;
    }

}
