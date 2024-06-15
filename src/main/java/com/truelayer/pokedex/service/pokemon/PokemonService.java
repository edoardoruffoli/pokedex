package com.truelayer.pokedex.service.pokemon;

import com.truelayer.pokedex.converter.PokemonConverter;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PokemonService {

    @Autowired
    private PokemonConverter pokemonConverter;

    public PokemonBean getPokemonByName(String name) {
        return null;
    }

}
