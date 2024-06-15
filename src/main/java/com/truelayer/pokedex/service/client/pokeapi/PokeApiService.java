package com.truelayer.pokedex.service.client.pokeapi;

import com.truelayer.pokedex.service.client.pokeapi.bean.PokemonSpeciesResponseBean;
import com.truelayer.pokedex.service.client.pokeapi.converter.PokemonSpeciesResponseBeanToBeanConverter;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PokeApiService {
    private static final Logger logger = LoggerFactory.getLogger(PokeApiService.class);

    @Autowired private PokeApiClient pokeApiClient;
    @Autowired private PokemonSpeciesResponseBeanToBeanConverter responseBeanToBeanConverter;

    public PokemonBean getPokemonData(String name) {
        logger.info("Getting {} data from PokeApi", name);
        PokemonSpeciesResponseBean pokemonSpeciesResponseBean = pokeApiClient.getPokemon(name);
        return responseBeanToBeanConverter.convert(pokemonSpeciesResponseBean);
    }

}

