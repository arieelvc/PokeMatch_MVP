package com.castan.pokematch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.castan.pokematch.dto.BattleResultDto;
import com.castan.pokematch.dto.PokemonDto;
import com.castan.pokematch.strategy.DamageCalculatorStrategy;

@Service
public class BattleSimulatorService {

    private final DamageCalculatorStrategy damageCalculatorStrategy;

    public BattleSimulatorService(DamageCalculatorStrategy damageCalculatorStrategy) {
        this.damageCalculatorStrategy = damageCalculatorStrategy;
    }

    public BattleResultDto simulateBattle(PokemonDto player, PokemonDto opponent) {
        List<String> log = new ArrayList<>();
        log.add("¡The battle begins between " + player.getName() + " and " + opponent.getName() + "!");

        // 1. Clonamos la vida (HP) en variables locales para poder restarla sin
        // modificar el DTO original
        int playerHp = player.getHp();
        int opponentHp = opponent.getHp();

        PokemonDto firstAttacker;
        PokemonDto secondAttacker;

        // 2. Determinar turnos por velocidad (Sistema de Iniciativa)
        if (player.getSpeed() >= opponent.getSpeed()) {
            firstAttacker = player;
            secondAttacker = opponent;
            log.add(player.getName() + " is faster (" + player.getSpeed() + " SPD) and takes the initiative.");
        } else {
            firstAttacker = opponent;
            secondAttacker = player;
            log.add(opponent.getName() + " is faster (" + opponent.getSpeed() + " SPD) and takes the initiative.");
        }

        // --- TURNO 1: El más rápido ataca ---
        // Extraemos tipos para la estrategia (tomando el primero de la lista de forma
        // segura)
        String type1 = firstAttacker.getTypes().isEmpty() ? "normal" : firstAttacker.getTypes().get(0);
        String type2 = secondAttacker.getTypes().isEmpty() ? "normal" : secondAttacker.getTypes().get(0);

        double multiplier1 = damageCalculatorStrategy.calculateMultiplier(type1, type2);
        int damage1 = calcularDanio(firstAttacker.getAttack(), secondAttacker.getDefense(), multiplier1);

        // Aplicamos el daño a la vida local del segundo
        if (secondAttacker == opponent) {
            opponentHp -= damage1;
        } else {
            playerHp -= damage1;
        }

        log.add(firstAttacker.getName() + " uses its basic attack. Deals " + damage1 + " damage to "
                + secondAttacker.getName() + " (Multiplier: x" + multiplier1 + ").");

        // --- VALIDACIÓN DE K.O. ---
        if (playerHp <= 0 || opponentHp <= 0) {
            log.add("!" + secondAttacker.getName() + " has been defeated!");
            return new BattleResultDto(firstAttacker.getName(), secondAttacker.getName(), multiplier1, log);
        }

        // --- TURNO 2: El sobreviviente contraataca ---
        double multiplier2 = damageCalculatorStrategy.calculateMultiplier(type2, type1);
        int damage2 = calcularDanio(secondAttacker.getAttack(), firstAttacker.getDefense(), multiplier2);

        if (firstAttacker == player) {
            playerHp -= damage2;
        } else {
            opponentHp -= damage2;
        }

        log.add(secondAttacker.getName() + " counterattacks strongly. Deals " + damage2 + " damage to "
                + firstAttacker.getName() + " (Multiplier: x" + multiplier2 + ").");

        // --- VEREDICTO FINAL (Si ambos quedan en pie, gana el que tenga más vida
        // residual) ---
        String winner;
        String loser;
        double finalMultiplierUsed;

        if (playerHp >= opponentHp) {
            winner = player.getName();
            loser = opponent.getName();
            finalMultiplierUsed = multiplier1; // Guardamos el del jugador para el reporte
        } else {
            winner = opponent.getName();
            loser = player.getName();
            finalMultiplierUsed = multiplier2;
        }

        log.add("¡The match is over! The winner is " + winner + ".");

        return new BattleResultDto(winner, loser, finalMultiplierUsed, log);
    }

    // Método auxiliar matemático para calcular el daño mitigado por la defensa
    private int calcularDanio(int attack, int defense, double multiplier) {
        // Fórmula RPG clásica: (Ataque * Multiplicador) - (Defensa / 2)
        double baseDamage = (attack * multiplier) - (defense * 0.5);
        // Garantizamos que al menos haga 2 de daño para que la defensa no absorba el
        // 100% del golpe
        return Math.max(2, (int) Math.round(baseDamage));
    }
}
