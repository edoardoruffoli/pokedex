package com.truelayer.pokedex.service.pokemon;

import com.truelayer.pokedex.service.client.funtranslations.FunTranslationsService;
import com.truelayer.pokedex.service.client.funtranslations.enums.CharacterEnum;
import com.truelayer.pokedex.service.client.pokeapi.PokeApiService;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PokemonService {
    protected static final String HABITAT_CAVE = "cave";

    @Autowired
    private PokeApiService pokeApiService;

    @Autowired
    private FunTranslationsService funTranslationsService;

    public PokemonBean getPokemonByName(String name) {
        return pokeApiService.getPokemonData(name);
    }

    public PokemonBean getPokemonByNameWithTranslation(String name) {
        PokemonBean pokemonBean = this.getPokemonByName(name);
        return pokemonBean.toBuilder()
                        .description(getTranslation(pokemonBean))
                        .build();
    }

    protected String getTranslation(PokemonBean pokemonBean) {
        if (HABITAT_CAVE.equals(pokemonBean.habitat()) || pokemonBean.isLegendary()) {
            return Optional.ofNullable(funTranslationsService.getTranslation(pokemonBean.description(), CharacterEnum.YODA))
                    .orElse(pokemonBean.description());
        }
        else {
            return Optional.ofNullable(funTranslationsService.getTranslation(pokemonBean.description(), CharacterEnum.SHAKESPEARE))
                    .orElse(pokemonBean.description());
        }
    }

}
