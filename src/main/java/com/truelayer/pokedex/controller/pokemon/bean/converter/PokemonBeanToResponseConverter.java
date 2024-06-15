package com.truelayer.pokedex.controller.pokemon.bean.converter;

import com.truelayer.pokedex.controller.pokemon.bean.PokemonResponseBean;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class PokemonBeanToResponseConverter implements Converter<PokemonBean, PokemonResponseBean> {

    public PokemonResponseBean convert(PokemonBean pokemonBean) {
        return PokemonResponseBean.builder()
                .name(pokemonBean.name())
                .description(pokemonBean.description())
                .habitat(pokemonBean.habitat())
                .isLegendary(pokemonBean.isLegendary())
                .build();
    }

}
