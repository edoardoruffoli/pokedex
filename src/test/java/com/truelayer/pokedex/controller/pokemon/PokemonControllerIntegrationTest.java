package com.truelayer.pokedex.controller.pokemon;

import com.truelayer.pokedex.BaseIntegrationTest;
import com.truelayer.pokedex.controller.pokemon.bean.PokemonResponseBean;
import com.truelayer.pokedex.service.client.funtranslations.FunTranslationsClientStub;
import com.truelayer.pokedex.service.client.funtranslations.enums.CharacterEnum;
import com.truelayer.pokedex.service.client.pokeapi.PokeApiClientStub;
import com.truelayer.pokedex.utils.FileUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@AutoConfigureWireMock(port = 0)
public class PokemonControllerIntegrationTest extends BaseIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getPokemonByName_legendaryPokemon() {
        String pokemonName = "mewtwo";
        String description = "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.";
        String habitat = "rare";
        Boolean isLegendary = true;

        String jsonResponse = FileUtils.readFileAsString("src/test/resources/response/pokeapi-mewtwo.json");
        PokeApiClientStub.stubGetPokemonCall(pokemonName, jsonResponse);

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
    void getPokemonByName_nullHabitatPokemon() {
        String pokemonName = "torterra";
        String description = "Small Pokémon occasionally gather on its unmoving back to begin building their nests.";
        String habitat = null;
        Boolean isLegendary = false;

        String jsonResponse = FileUtils.readFileAsString("src/test/resources/response/pokeapi-torterra.json");
        PokeApiClientStub.stubGetPokemonCall(pokemonName, jsonResponse);

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
    void getPokemonByName_nonExistingPokemon() {
        String pokemonName = "pippo";
        PokeApiClientStub.stubGetNonExistingPokemonCall(pokemonName);

        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/%s".formatted(port, pokemonName), String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isEqualTo("Pokemon not found on PokeApi");
    }

    @Test
    void getPokemonByName_pokeApiError() {
        String pokemonName = "error";
        PokeApiClientStub.stubGetPokemonCallWithGenericError(pokemonName);

        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/%s".formatted(port, pokemonName), String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(500);
        assertThat(response.getBody()).isEqualTo("Internal server error");
    }

    @Test
    void getPokemonByNameWithTranslation_yodaTranslation_legendaryPokemon() {
        String pokemonName = "mewtwo";
        String description = "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.";
        String translatedDescription = "Created by a scientist after years of horrific gene splicing and dna engineering experiments,  it was.";
        String habitat = "rare";
        Boolean isLegendary = true;

        String pokeApiResponse = FileUtils.readFileAsString("src/test/resources/response/pokeapi-mewtwo.json");
        String funTranslationsResponse = FileUtils.readFileAsString("src/test/resources/response/funtranslations-mewtwo.json");
        PokeApiClientStub.stubGetPokemonCall(pokemonName, pokeApiResponse);
        FunTranslationsClientStub.stubTranslationCall(description, CharacterEnum.YODA.toString(), funTranslationsResponse);

        ResponseEntity<PokemonResponseBean> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/%s".formatted(port, pokemonName), PokemonResponseBean.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(PokemonResponseBean.builder()
                .name(pokemonName)
                .description(translatedDescription)
                .habitat(habitat)
                .isLegendary(isLegendary)
                .build());
    }

    @Test
    void getPokemonByNameWithTranslation_yodaTranslation_cavePokemon() {
        String pokemonName = "onix";
        String description = "As it grows, the stone portions of its body harden to become similar to a diamond, but colored black.";
        String translatedDescription = "As it grows,To become similar to a diamond,  the stone portions of its body harden,But colored black.";
        String habitat = "cave";
        Boolean isLegendary = false;

        String pokeApiResponse = FileUtils.readFileAsString("src/test/resources/response/pokeapi-onix.json");
        String funTranslationsResponse = FileUtils.readFileAsString("src/test/resources/response/funtranslations-onix.json");
        PokeApiClientStub.stubGetPokemonCall(pokemonName, pokeApiResponse);
        FunTranslationsClientStub.stubTranslationCall(description, CharacterEnum.YODA.toString(), funTranslationsResponse);

        ResponseEntity<PokemonResponseBean> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/%s".formatted(port, pokemonName), PokemonResponseBean.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(PokemonResponseBean.builder()
                .name(pokemonName)
                .description(translatedDescription)
                .habitat(habitat)
                .isLegendary(isLegendary)
                .build());
    }

    @Test
    void getPokemonByNameWithTranslation_shakespeareTranslation() {
        String pokemonName = "torterra";
        String description = "Small Pokémon occasionally gather on its unmoving back to begin building their nests.";
        String translatedDescription = "Bawbling pokémon occasionally gather on its unmoving back to beginneth building their aeries.";
        String habitat = null;
        Boolean isLegendary = false;

        String pokeApiResponse = FileUtils.readFileAsString("src/test/resources/response/pokeapi-torterra.json");
        String funTranslationsResponse = FileUtils.readFileAsString("src/test/resources/response/funtranslations-torterra.json");
        PokeApiClientStub.stubGetPokemonCall(pokemonName, pokeApiResponse);
        FunTranslationsClientStub.stubTranslationCall(description, CharacterEnum.SHAKESPEARE.toString(), funTranslationsResponse);

        ResponseEntity<PokemonResponseBean> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/%s".formatted(port, pokemonName), PokemonResponseBean.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(PokemonResponseBean.builder()
                .name(pokemonName)
                .description(translatedDescription)
                .habitat(habitat)
                .isLegendary(isLegendary)
                .build());
    }

    @Test
    void getPokemonByNameWithTranslation_funTranslationError_fallbackDefaultDescription() {
        String pokemonName = "mewtwo";
        String description = "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.";
        String habitat = "rare";
        Boolean isLegendary = true;

        String pokeApiResponse = FileUtils.readFileAsString("src/test/resources/response/pokeapi-mewtwo.json");
        String funTranslationsResponse = FileUtils.readFileAsString("src/test/resources/response/funtranslations-too-many-requests.json");
        PokeApiClientStub.stubGetPokemonCall(pokemonName, pokeApiResponse);
        FunTranslationsClientStub.stubGetTranslationCallWithTooManyRequestsError(description, CharacterEnum.YODA.toString(), funTranslationsResponse);

        ResponseEntity<PokemonResponseBean> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/%s".formatted(port, pokemonName), PokemonResponseBean.class);

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
    void getPokemonByNameWithTranslation_nonExistingPokemon() {
        String pokemonName = "pippo";
        PokeApiClientStub.stubGetNonExistingPokemonCall(pokemonName);

        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/%s".formatted(port, pokemonName), String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isEqualTo("Pokemon not found on PokeApi");
    }

    @Test
    void getPokemonByNameWithTranslation_pokeApiError() {
        String pokemonName = "error";
        PokeApiClientStub.stubGetPokemonCallWithGenericError(pokemonName);

        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/%s".formatted(port, pokemonName), String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(500);
        assertThat(response.getBody()).isEqualTo("Internal server error");
    }
}
