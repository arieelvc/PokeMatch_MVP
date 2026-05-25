package com.castan.pokematch.strategy;

import org.springframework.stereotype.Component;

import com.castan.pokematch.dto.PokemonType;

@Component
public class TypeAdvantageStrategy implements DamageCalculatorStrategy {

    @Override
    public double calculateMultiplier(String attackerType, String defenderType) {
        try {
            // Convertir los Strings a Enum de forma segura
            PokemonType attacker = PokemonType.valueOf(attackerType.toUpperCase());
            PokemonType defender = PokemonType.valueOf(defenderType.toUpperCase());

            // Consultar el EFFECTIVENESS_MAP
            var defenderRules = PokemonTypeMatrix.getEffectivenessMap().get(attacker);
            if (defenderRules != null) {
                Double multiplier = defenderRules.get(defender);
                if (multiplier != null) {
                    return multiplier;
                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            // Si el tipo no existe en el Enum o llega null, el daño es neutro (1.0)
            return 1.0;
        }

        return 1.0;
    }

}
