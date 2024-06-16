package com.truelayer.pokedex.service.client.funtranslations.configuration;

import com.truelayer.pokedex.service.client.funtranslations.exception.FunTranslationsException;
import com.truelayer.pokedex.service.client.funtranslations.exception.FunTranslationsTooManyRequestsException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FunTranslationsErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 429 -> new FunTranslationsTooManyRequestsException();
            default -> new FunTranslationsException();
        };
    }

}
