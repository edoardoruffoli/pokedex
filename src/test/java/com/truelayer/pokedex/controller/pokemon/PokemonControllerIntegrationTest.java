package com.truelayer.pokedex.controller.pokemon;

import com.truelayer.pokedex.BaseIntegrationTest;
import com.truelayer.pokedex.controller.pokemon.bean.PokemonResponseBean;
import com.truelayer.pokedex.service.client.pokeapi.PokeApiClient;
import com.truelayer.pokedex.service.client.pokeapi.bean.PokemonSpeciesResponseBean;
import com.truelayer.pokedex.service.client.pokeapi.exception.PokeApiException;
import com.truelayer.pokedex.service.client.pokeapi.exception.PokeApiNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


public class PokemonControllerIntegrationTest extends BaseIntegrationTest {

    @MockBean
    private PokeApiClient pokeApiClient;

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getLegendaryPokemon() {
        String pokemonName = "mewtwo";
        String description = "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.";
        String habitat = "rare";
        Boolean isLegendary = true;
        PokemonSpeciesResponseBean speciesResponseBean = PokemonSpeciesResponseBean.builder()
                .name(pokemonName)
                .flavorTextEntries(List.of(
                        PokemonSpeciesResponseBean.PokemonFlavorTextResponseBean.builder()
                                .flavorText(description)
                        .build()))
                .habitat(PokemonSpeciesResponseBean.PokemonHabitatResponseBean.builder()
                        .name(habitat)
                        .build())
                .isLegendary(isLegendary)
                .build();
        when(pokeApiClient.getPokemon(any())).thenReturn(speciesResponseBean);

        ResponseEntity<PokemonResponseBean> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/%s".formatted(port, pokemonName), PokemonResponseBean.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(PokemonResponseBean.builder()
                .name(pokemonName)
                .description(description)
                .habitat(habitat)
                .isLegendary(isLegendary)
                .build());
    }

    @Test
    void getNonExistingPokemon() {
        String pokemonName = "pippo";
        doThrow(PokeApiNotFoundException.class).when(pokeApiClient).getPokemon(any());

        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/%s".formatted(port, pokemonName), String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isEqualTo("Pokemon not found on PokeApi");
    }

    @Test
    void getPokemonPokeApiError() {
        String pokemonName = "pikachu";
        doThrow(PokeApiException.class).when(pokeApiClient).getPokemon(any());

        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/%s".formatted(port, pokemonName), String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(500);
        assertThat(response.getBody()).isEqualTo("Internal server error");
    }
}
