package com.truelayer.pokedex.service.client.pokeapi.configuration;

import com.truelayer.pokedex.service.client.pokeapi.exception.PokeApiException;
import com.truelayer.pokedex.service.client.pokeapi.exception.PokeApiNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class PokeApiErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 404 -> new PokeApiNotFoundException();
            default -> new PokeApiException();
        };
    }

}
