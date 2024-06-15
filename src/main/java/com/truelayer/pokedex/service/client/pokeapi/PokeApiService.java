package com.truelayer.pokedex.service.client.pokeapi;

import com.truelayer.pokedex.converter.PokemonConverter;
import com.truelayer.pokedex.service.client.pokeapi.bean.PokemonSpeciesResponseBean;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PokeApiService {
    private static final Logger logger = LoggerFactory.getLogger(PokeApiService.class);

    @Autowired private PokeApiClient pokeApiClient;
    @Autowired private PokemonConverter pokemonConverter;

    public PokemonBean getPokemonData(String name) {
        logger.info("Getting {} data from PokeApi", name);
        PokemonSpeciesResponseBean pokemonSpeciesResponseBean = pokeApiClient.getPokemon(name);
        return pokemonConverter.toBean(pokemonSpeciesResponseBean);
    }

}

