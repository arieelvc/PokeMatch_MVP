package com.castan.pokematch.mapper;

import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.castan.pokematch.dto.PokeApiPokemonResponse;
import com.castan.pokematch.dto.PokemonDto;

@Mapper(componentModel = "spring")
public interface PokemonMapper {

    @Mapping(source = "sprites.front_default", target = "imageUrl")
    @Mapping(target = "hp", ignore = true) // Se ignoran temporalmente en el mapeo automático
    @Mapping(target = "attack", ignore = true) // porque los llenaremos en el paso posterior.
    @Mapping(target = "defense", ignore = true)
    @Mapping(target = "speed", ignore = true)
    @Mapping(target = "types", ignore = true)
    PokemonDto toDto(PokeApiPokemonResponse response);

    // 2. Mapeo Personalizado: Este método se ejecuta automáticamente justo después
    // de que MapStruct termina el mapeo plano de arriba.
    @AfterMapping
    default void mapComplexFields(PokeApiPokemonResponse source, @MappingTarget PokemonDto target) {
        if (source == null || target == null) {
            return;
        }

        // Mapeo de Estadísticas usando tus Streams corregidos con sintaxis de Records
        if (source.stats() != null) {
            target.setHp(extractStat(source.stats(), "hp"));
            target.setAttack(extractStat(source.stats(), "attack"));
            target.setDefense(extractStat(source.stats(), "defense"));
            target.setSpeed(extractStat(source.stats(), "speed"));
        }

        // Mapeo de Tipos simplificado a List<String>
        if (source.types() != null) {
            target.setTypes(source.types().stream()
                    .map(typeSlot -> typeSlot.type().name())
                    .toList());
        }
    }

    // Método auxiliar para evitar repetir la lógica del Stream por cada estadística
    default int extractStat(List<PokeApiPokemonResponse.StatSlot> stats, String statName) {
        return stats.stream()
                .filter(slot -> statName.equals(slot.stat().name()))
                .findFirst()
                .map(PokeApiPokemonResponse.StatSlot::base_stat)
                .orElse(0);
    }

}
