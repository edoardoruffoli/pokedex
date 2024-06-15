package com.truelayer.pokedex.service.client.pokeapi;

import com.truelayer.pokedex.BaseUnitTest;
import com.truelayer.pokedex.service.client.pokeapi.bean.PokemonSpeciesResponseBean;
import com.truelayer.pokedex.service.client.pokeapi.converter.PokemonSpeciesResponseBeanToBeanConverter;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class PokeApiServiceTest extends BaseUnitTest {

    @Mock private PokeApiClient pokeApiClient;
    @Mock private PokemonSpeciesResponseBeanToBeanConverter responseBeanToBeanConverter;
    @InjectMocks
    private PokeApiService service;


    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(
                pokeApiClient,
                responseBeanToBeanConverter);
    }

    @Test
    void getPokemonData_shouldGet() {
        // given
        String name = "name";
        PokemonBean pokemonBean = random(PokemonBean.class);
        PokemonSpeciesResponseBean pokemonSpeciesResponseBean = random(PokemonSpeciesResponseBean.class);

        when(pokeApiClient.getPokemon(any())).thenReturn(pokemonSpeciesResponseBean);
        when(responseBeanToBeanConverter.convert(any())).thenReturn(pokemonBean);

        // when
        PokemonBean res = service.getPokemonData(name);

        // verify
        assertThat(res)
                .isEqualTo(pokemonBean);
        verify(pokeApiClient).getPokemon(name);
        verify(responseBeanToBeanConverter).convert(pokemonSpeciesResponseBean);
    }

}