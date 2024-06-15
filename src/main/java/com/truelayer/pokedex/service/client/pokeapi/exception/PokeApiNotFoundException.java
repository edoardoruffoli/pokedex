package com.truelayer.pokedex.service.client.pokeapi.exception;

public class PokeApiNotFoundException extends PokeApiException {
    public PokeApiNotFoundException() {
        super("PokeApi resource not found");
    }

    public PokeApiNotFoundException(String message) {
        super(message);
    }

    public PokeApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

