package com.truelayer.pokedex.converter;

import com.truelayer.pokedex.controller.pokemon.bean.PokemonResponseBean;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.springframework.stereotype.Component;


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
}
