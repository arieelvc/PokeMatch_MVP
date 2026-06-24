package com.castan.pokematch.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TypeAdvantageStrategyTest {
    private TypeAdvantageStrategy strategy;

    @BeforeEach
    void setUp() {
        // Inicializamos la estrategia de forma aislada y rápida antes de cada test
        this.strategy = new TypeAdvantageStrategy();
    }

    @Test
    @DisplayName("Debería retornar multiplicador 2.0 cuando FUEGO ataca a PLANTA")
    void shouldReturnSuperEffectiveMultiplier() {
        // Act
        double multiplier = strategy.calculateMultiplier("FIRE", "PLANT");

        // Assert
        assertEquals(2.0, multiplier, "Fuego debería ser súper efectivo contra Planta");
    }

    @Test
    @DisplayName("Debería retornar multiplicador 0.5 cuando AGUA ataca a PLANTA")
    void shouldReturnNotVeryEffectiveMultiplier() {
        // Act
        double multiplier = strategy.calculateMultiplier("WATER", "PLANT");

        // Assert
        assertEquals(0.5, multiplier, "Agua debería hacer la mitad de daño a Planta");
    }

    @Test
    @DisplayName("Debería retornar multiplicador 1.0 para tipos no mapeados o strings inválidos")
    void shouldReturnNeutralMultiplierForUnknownTypes() {
        // Act & Assert
        double multiplierUnknown = strategy.calculateMultiplier("ROCK", "GHOST");
        double multiplierInvalid = strategy.calculateMultiplier("INVALID", "FIRE");

        assertEquals(1.0, multiplierUnknown, "Tipos no soportados en el MVP deben ser neutros (1.0)");
        assertEquals(1.0, multiplierInvalid, "Strings inválidos deben ser manejados por el catch y devolver 1.0");
    }
}
