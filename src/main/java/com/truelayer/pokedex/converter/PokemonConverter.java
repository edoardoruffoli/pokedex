package com.truelayer.pokedex.converter;

import com.truelayer.pokedex.controller.pokemon.bean.PokemonResponseBean;
import com.truelayer.pokedex.service.client.pokeapi.bean.PokemonSpeciesResponseBean;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class PokemonConverter {

    public PokemonResponseBean toResponseBean(PokemonBean pokemonBean) {
        return PokemonResponseBean.builder()
                .name(pokemonBean.name())
                .description(pokemonBean.description())
                .habitat(pokemonBean.habitat())
                .isLegendary(pokemonBean.isLegendary())
                .build();
    }

    public PokemonBean toBean(PokemonSpeciesResponseBean pokemonSpeciesResponseBean) {
        return PokemonBean.builder()
                .name(pokemonSpeciesResponseBean.name())
                .description(Optional.ofNullable(pokemonSpeciesResponseBean.flavorTextEntries())
                        .flatMap(entries -> entries.stream()
                                .findFirst()
                                .map(PokemonSpeciesResponseBean.PokemonFlavorTextResponseBean::flavorText)
                                .map(description -> description.replaceAll("\\s", " ")))
                        .orElse(null))
                .isLegendary(pokemonSpeciesResponseBean.isLegendary())
                .habitat(Optional.ofNullable(pokemonSpeciesResponseBean.habitat())
                        .map(PokemonSpeciesResponseBean.PokemonHabitatResponseBean::name)
                        .orElse(null))
                .build();
    }
}
