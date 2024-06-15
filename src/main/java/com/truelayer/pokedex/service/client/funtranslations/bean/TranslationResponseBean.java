package com.truelayer.pokedex.service.client.funtranslations.bean;

import com.fasterxml.jackson.annotation.JsonProperty;


public record TranslationResponseBean(@JsonProperty("contents") TranslationContentBean contentBean) {

    public record TranslationContentBean(@JsonProperty("translated") String translated) {
    }

}