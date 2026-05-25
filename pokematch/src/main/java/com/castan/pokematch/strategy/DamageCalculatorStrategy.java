package com.castan.pokematch.strategy;

public interface DamageCalculatorStrategy {

    double calculateMultiplier(String attackerType, String defenderType);

}
