package com.truelayer.pokedex.controller.pokemon;

import com.truelayer.pokedex.controller.pokemon.bean.PokemonResponseBean;
import com.truelayer.pokedex.controller.pokemon.bean.converter.PokemonBeanToResponseConverter;
import com.truelayer.pokedex.service.pokemon.PokemonService;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PokemonDelegate {
    private static final Logger logger = LoggerFactory.getLogger(PokemonDelegate.class);

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private PokemonBeanToResponseConverter beanToResponseConverter;

    public PokemonResponseBean getPokemonByName(String name) {
        PokemonBean pokemonBean = pokemonService.getPokemonByName(name);
        return beanToResponseConverter.convert(pokemonBean);
    }

    public PokemonResponseBean getPokemonByNameWithTranslation(String name) {
        PokemonBean pokemonBean = pokemonService.getPokemonByNameWithTranslation(name);
        return beanToResponseConverter.convert(pokemonBean);
    }

}
