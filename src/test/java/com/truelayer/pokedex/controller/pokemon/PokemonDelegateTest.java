package com.truelayer.pokedex.controller.pokemon;

import com.truelayer.pokedex.BaseUnitTest;
import com.truelayer.pokedex.controller.pokemon.bean.PokemonResponseBean;
import com.truelayer.pokedex.converter.PokemonConverter;
import com.truelayer.pokedex.service.pokemon.PokemonService;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class PokemonDelegateTest extends BaseUnitTest {

    @Mock private PokemonService pokemonService;
    @Mock private PokemonConverter pokemonConverter;
    @InjectMocks
    private PokemonDelegate delegate;


    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(
                pokemonService,
                pokemonConverter);
    }

    @Test
    void getPokemonByName_shouldGet() {
        // given
        String name = "name";
        PokemonBean pokemonBean = mock(PokemonBean.class);
        PokemonResponseBean pokemonResponseBean = mock(PokemonResponseBean.class);

        when(pokemonService.getPokemonByName(any())).thenReturn(pokemonBean);
        when(pokemonConverter.toResponseBean(any())).thenReturn(pokemonResponseBean);

        // when
        PokemonResponseBean res = delegate.getPokemonByName(name);

        // verify
        assertThat(res)
                .isEqualTo(pokemonResponseBean);
        verify(pokemonService).getPokemonByName(name);
        verify(pokemonConverter).toResponseBean(pokemonBean);
    }

}