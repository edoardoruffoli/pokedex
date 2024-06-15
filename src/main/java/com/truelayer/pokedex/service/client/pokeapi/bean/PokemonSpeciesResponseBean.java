package com.truelayer.pokedex.service.client.pokeapi.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record PokemonSpeciesResponseBean(@JsonProperty("name") String name,
                                         @JsonProperty("is_legendary") Boolean isLegendary,
                                         @JsonProperty("flavor_text_entries") List<PokemonFlavorTextResponseBean> flavorTextEntries,
                                         @JsonProperty("habitat") PokemonHabitatResponseBean habitat) {
    @Builder
    public record PokemonFlavorTextResponseBean(@JsonProperty("flavor_text") String flavorText) {}
    @Builder
    public record PokemonHabitatResponseBean(@JsonProperty("name") String name) {}

}