package com.castan.pokematch.dto;

import java.util.List;

public record BattleResultDto(
        String winnerName,
        String loserName,
        double multiplierUsed,
        List<String> battleLog) {
}
