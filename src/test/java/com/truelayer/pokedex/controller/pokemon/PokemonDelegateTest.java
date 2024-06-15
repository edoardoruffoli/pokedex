package com.truelayer.pokedex.controller.pokemon;

import com.truelayer.pokedex.BaseUnitTest;
import com.truelayer.pokedex.controller.pokemon.bean.PokemonResponseBean;
import com.truelayer.pokedex.controller.pokemon.bean.converter.PokemonBeanToResponseConverter;
import com.truelayer.pokedex.service.pokemon.PokemonService;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class PokemonDelegateTest extends BaseUnitTest {

    @Mock private PokemonService pokemonService;
    @Mock private PokemonBeanToResponseConverter beanToResponseConverter;
    @InjectMocks
    private PokemonDelegate delegate;


    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(
                pokemonService,
                beanToResponseConverter);
    }

    @Test
    void getPokemonByName_shouldGet() {
        // given
        String name = "name";
        PokemonBean pokemonBean = random(PokemonBean.class);
        PokemonResponseBean pokemonResponseBean = random(PokemonResponseBean.class);

        when(pokemonService.getPokemonByName(any())).thenReturn(pokemonBean);
        when(beanToResponseConverter.convert(any())).thenReturn(pokemonResponseBean);

        // when
        PokemonResponseBean res = delegate.getPokemonByName(name);

        // verify
        assertThat(res)
                .isEqualTo(pokemonResponseBean);
        verify(pokemonService).getPokemonByName(name);
        verify(beanToResponseConverter).convert(pokemonBean);
    }

}