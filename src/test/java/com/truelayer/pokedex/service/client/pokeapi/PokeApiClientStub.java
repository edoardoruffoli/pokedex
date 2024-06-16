package com.truelayer.pokedex.service.client.pokeapi;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class PokeApiClientStub {
    public static void stubGetPokemonCall(String name, String response) {
        stubFor(get(urlEqualTo("/api/v2/pokemon-species/" + name))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
    }

    public static void stubGetNonExistingPokemonCall(String name) {
        stubFor(get(urlEqualTo("/api/v2/pokemon-species/" + name))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody("Not Found")));
    }

    public static void stubGetPokemonCallWithGenericError(String name) {
        stubFor(get(urlEqualTo("/api/v2/pokemon-species/" + name))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")));
    }
}
