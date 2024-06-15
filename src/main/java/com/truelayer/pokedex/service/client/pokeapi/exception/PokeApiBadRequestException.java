package com.truelayer.pokedex.service.client.pokeapi.exception;

public class PokeApiBadRequestException extends PokeApiException {
    public PokeApiBadRequestException() {
        super("Bad request to PokeApi");
    }

    public PokeApiBadRequestException(String message) {
        super(message);
    }

    public PokeApiBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
