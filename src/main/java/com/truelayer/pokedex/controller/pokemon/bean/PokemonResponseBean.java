package com.truelayer.pokedex.controller.pokemon.bean;

import lombok.Builder;

@Builder
public record PokemonResponseBean(String name,
                                  String description,
                                  Boolean isLegendary,
                                  String habitat) {
}
