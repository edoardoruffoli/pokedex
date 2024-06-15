package com.truelayer.pokedex.service.pokemon;

import com.truelayer.pokedex.service.client.pokeapi.PokeApiService;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PokemonService {
    @Autowired
    private PokeApiService pokeApiService;

    public PokemonBean getPokemonByName(String name) {
        return pokeApiService.getPokemonData(name);
    }

}
