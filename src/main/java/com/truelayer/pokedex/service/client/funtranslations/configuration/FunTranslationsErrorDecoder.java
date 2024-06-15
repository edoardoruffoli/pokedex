package com.truelayer.pokedex.service.client.funtranslations.configuration;

import com.truelayer.pokedex.service.client.funtranslations.exception.FunTranslationsException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FunTranslationsErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
            return new FunTranslationsException();
    }

}
