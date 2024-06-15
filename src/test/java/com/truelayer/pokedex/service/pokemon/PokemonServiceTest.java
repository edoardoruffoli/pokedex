package com.truelayer.pokedex.service.pokemon;

import com.truelayer.pokedex.BaseUnitTest;
import com.truelayer.pokedex.service.client.funtranslations.FunTranslationsService;
import com.truelayer.pokedex.service.client.funtranslations.enums.CharacterEnum;
import com.truelayer.pokedex.service.client.pokeapi.PokeApiService;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static com.truelayer.pokedex.service.pokemon.PokemonService.HABITAT_CAVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class PokemonServiceTest extends BaseUnitTest {

    @Mock private PokemonService pokemonService;
    @Mock private PokeApiService pokeApiService;
    @Mock private FunTranslationsService funTranslationsService;
    @InjectMocks @Spy
    private PokemonService service;


    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(
                pokemonService,
                pokeApiService,
                funTranslationsService);
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

    @Test
    void getPokemonByNameWithTranslation_shouldGet() {
        // given
        String name = "name";
        String description = "translated_description";
        PokemonBean pokemonBean = random(PokemonBean.class);

        doReturn(pokemonBean).when(service).getPokemonByName(any());
        doReturn(description).when(service).getTranslation(any());

        // when
        PokemonBean res = service.getPokemonByNameWithTranslation(name);

        // verify
        assertThat(res).isEqualTo(pokemonBean.toBuilder()
                        .description(description)
                        .build());
        verify(service).getPokemonByNameWithTranslation(name);
        verify(service).getTranslation(pokemonBean);
    }

    @Test
    void getTranslation_shouldGetYodaTranslation_caveHabitat() {
        // given
        PokemonBean pokemonBean = random(PokemonBean.class).toBuilder()
                .habitat(HABITAT_CAVE)
                .build();
        String translation = random(String.class);

        when(funTranslationsService.getTranslation(any(), any())).thenReturn(translation);

        // when
        String res = service.getTranslation(pokemonBean);

        // verify
        assertThat(res)
                .isEqualTo(translation);
        verify(funTranslationsService).getTranslation(pokemonBean.description(), CharacterEnum.YODA);
    }

    @Test
    void getTranslation_shouldGetYodaTranslation_isLegendary() {
        // given
        PokemonBean pokemonBean = random(PokemonBean.class).toBuilder()
                .isLegendary(true)
                .build();
        String translation = random(String.class);

        when(funTranslationsService.getTranslation(any(), any())).thenReturn(translation);

        // when
        String res = service.getTranslation(pokemonBean);

        // verify
        assertThat(res)
                .isEqualTo(translation);
        verify(funTranslationsService).getTranslation(pokemonBean.description(), CharacterEnum.YODA);
    }

    @Test
    void getTranslation_shouldNotGetYodaTranslation() {
        // given
        PokemonBean pokemonBean = random(PokemonBean.class).toBuilder()
                .isLegendary(true)
                .build();

        when(funTranslationsService.getTranslation(any(), any())).thenReturn(null);

        // when
        String res = service.getTranslation(pokemonBean);

        // verify
        assertThat(res)
                .isEqualTo(pokemonBean.description());
        verify(funTranslationsService).getTranslation(pokemonBean.description(), CharacterEnum.YODA);
    }

    @Test
    void getTranslation_shouldGetShakespeareTranslation() {
        // given
        PokemonBean pokemonBean = random(PokemonBean.class).toBuilder()
                .isLegendary(false)
                .build();
        String translation = random(String.class);

        when(funTranslationsService.getTranslation(any(), any())).thenReturn(translation);

        // when
        String res = service.getTranslation(pokemonBean);

        // verify
        assertThat(res)
                .isEqualTo(translation);
        verify(funTranslationsService).getTranslation(pokemonBean.description(), CharacterEnum.SHAKESPEARE);
    }

    @Test
    void getTranslation_shouldNotGetShakespeareTranslation() {
        // given
        PokemonBean pokemonBean = random(PokemonBean.class).toBuilder()
                .isLegendary(false)
                .build();

        when(funTranslationsService.getTranslation(any(), any())).thenReturn(null);

        // when
        String res = service.getTranslation(pokemonBean);

        // verify
        assertThat(res)
                .isEqualTo(pokemonBean.description());
        verify(funTranslationsService).getTranslation(pokemonBean.description(), CharacterEnum.SHAKESPEARE);
    }

}