package com.castan.pokematch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.castan.pokematch.dto.BattleResultDto;
import com.castan.pokematch.dto.PokemonDto;
import com.castan.pokematch.strategy.DamageCalculatorStrategy;

@ExtendWith(MockitoExtension.class)
public class BattleSimulatorServiceTest {

    private BattleSimulatorService battleSimulatorService;

    @Mock
    private DamageCalculatorStrategy damageCalculatorStrategy;

    @BeforeEach
    void setUp() {
        // Inyectamos el mock manualmente en el servicio
        this.battleSimulatorService = new BattleSimulatorService(damageCalculatorStrategy);
    }

    @Test
    @DisplayName("Debería ganar el Pokémon más rápido por K.O. en el primer turno")
    void shouldWinBattleInFirstTurnWhenAttackerIsFasterAndStronger() {
        // Arrange (Preparar los datos de prueba)
        PokemonDto fastAttacker = new PokemonDto();
        fastAttacker.setName("Pikachu");
        fastAttacker.setHp(35);
        fastAttacker.setAttack(55);
        fastAttacker.setDefense(40);
        fastAttacker.setSpeed(90);
        fastAttacker.setTypes(List.of("ELECTRIC"));

        PokemonDto slowDefender = new PokemonDto();
        slowDefender.setName("Squirtle");
        slowDefender.setHp(44);
        slowDefender.setAttack(48);
        slowDefender.setDefense(65);
        slowDefender.setSpeed(43);
        slowDefender.setTypes(List.of("WATER"));

        // Definimos el comportamiento del Mock: Cuando el servicio pregunte por
        // ELECTRIC vs WATER, responderá 2.0
        when(damageCalculatorStrategy.calculateMultiplier("ELECTRIC", "WATER")).thenReturn(2.0);

        // Act (Ejecutar la acción)
        BattleResultDto result = battleSimulatorService.simulateBattle(fastAttacker, slowDefender);

        // Assert (Verificar los resultados)
        assertNotNull(result);
        assertEquals("Pikachu", result.winnerName(), "Pikachu debería ganar por ser más rápido y letal");
        assertEquals("Squirtle", result.loserName(), "Squirtle debería ser el perdedor");
        assertEquals(2.0, result.multiplierUsed());

        // Verificamos que el log contenga la crónica del K.O.
        List<String> log = result.battleLog();
        System.out.println(log); // Opcional: para ver la crónica en consola al correr el test

        assertEquals("!Squirtle has been defeated!", log.get(log.size() - 1));
    }

}
