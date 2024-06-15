package com.truelayer.pokedex.service.client.pokeapi.configuration;

import com.truelayer.pokedex.service.client.pokeapi.exception.PokeApiBadRequestException;
import com.truelayer.pokedex.service.client.pokeapi.exception.PokeApiException;
import com.truelayer.pokedex.service.client.pokeapi.exception.PokeApiNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class PokeApiErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new PokeApiBadRequestException();
            case 404 -> new PokeApiNotFoundException();
            default -> new PokeApiException("Generic exception");
        };
    }

}
