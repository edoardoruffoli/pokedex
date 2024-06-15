package com.truelayer.pokedex.controller.pokemon;

import com.truelayer.pokedex.controller.pokemon.bean.PokemonResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonDelegate pokemonDelegate;

    @GetMapping("/{name}")
    public PokemonResponseBean getPokemonByName(@PathVariable @NonNull final String name) {
        return pokemonDelegate.getPokemonByName(name);
    }

}
