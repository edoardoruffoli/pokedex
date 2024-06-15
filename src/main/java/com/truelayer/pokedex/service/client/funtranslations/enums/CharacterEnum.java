package com.truelayer.pokedex.service.client.funtranslations.enums;

public enum CharacterEnum {
    YODA, SHAKESPEARE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
