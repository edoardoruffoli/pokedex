package com.truelayer.pokedex.service.client.pokeapi.converter;

import com.truelayer.pokedex.service.client.pokeapi.bean.PokemonSpeciesResponseBean;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class PokemonSpeciesResponseBeanToBeanConverter implements Converter<PokemonSpeciesResponseBean, PokemonBean> {

    @Override
    public PokemonBean convert(PokemonSpeciesResponseBean pokemonSpeciesResponseBean) {
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
