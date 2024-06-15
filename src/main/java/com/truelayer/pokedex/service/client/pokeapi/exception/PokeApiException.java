package com.truelayer.pokedex.service.client.pokeapi.exception;

public class PokeApiException extends RuntimeException {
    public PokeApiException(String message) {
        super(message);
    }

    public PokeApiException(String message, Throwable cause) {
        super(message, cause);
    }
}