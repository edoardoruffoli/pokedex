package com.truelayer.pokedex.service.client.funtranslations;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class FunTranslationsClientStub {
    public static void stubTranslationCall(String description, String character, String response) {
        stubFor(post(urlEqualTo("/translate/" + character))
                .withRequestBody(equalToJson(String.format("{ \"text\": \"%s\"}", description)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
    }

    public static void stubGetTranslationCallWithTooManyRequestsError(String description, String character, String response) {
        stubFor(post(urlEqualTo("/translate/" + character))
                .withRequestBody(equalToJson(String.format("{ \"text\": \"%s\"}", description)))
                .willReturn(aResponse()
                        .withStatus(429)
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
    }

}
