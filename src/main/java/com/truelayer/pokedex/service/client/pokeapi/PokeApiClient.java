package com.truelayer.pokedex.service.client.pokeapi;

import com.truelayer.pokedex.service.client.pokeapi.bean.PokemonSpeciesResponseBean;
import com.truelayer.pokedex.service.client.pokeapi.configuration.PokeApiClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@FeignClient(name = "pokeApiClient", url = "${pokeapi.base.url}", configuration = PokeApiClientConfiguration.class)
public interface PokeApiClient {
        @RequestMapping(method = GET, path = "/api/v2/pokemon-species/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
        PokemonSpeciesResponseBean getPokemon(@PathVariable("name") String name);
}