package com.truelayer.pokedex.service.pokemon;

import com.truelayer.pokedex.BaseUnitTest;
import com.truelayer.pokedex.service.client.pokeapi.PokeApiService;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class PokemonServiceTest extends BaseUnitTest {

    @Mock private PokemonService pokemonService;
    @Mock private PokeApiService pokeApiService;
    @InjectMocks
    private PokemonService service;


    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(
                pokemonService,
                pokeApiService);
    }

    @Test
    void getPokemonByName_shouldGet() {
        // given
        String name = "name";
        PokemonBean pokemonBean = random(PokemonBean.class);

        when(pokeApiService.getPokemonData(any())).thenReturn(pokemonBean);

        // when
        PokemonBean res = service.getPokemonByName(name);

        // verify
        assertThat(res)
                .isEqualTo(pokemonBean);
        verify(pokeApiService).getPokemonData(name);
    }

}