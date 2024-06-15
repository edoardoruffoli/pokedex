package com.truelayer.pokedex.service.pokemon.bean;

import lombok.Builder;

@Builder(toBuilder = true)
public record PokemonBean(String name,
                          String description,
                          Boolean isLegendary,
                          String habitat) {
}

