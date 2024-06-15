package com.truelayer.pokedex.service.client.pokeapi.converter;

import com.truelayer.pokedex.BaseUnitTest;
import com.truelayer.pokedex.service.client.pokeapi.bean.PokemonSpeciesResponseBean;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PokemonSpeciesResponseBeanToBeanConverterTest extends BaseUnitTest {

    @InjectMocks PokemonSpeciesResponseBeanToBeanConverter converter;

    @Test
    public void convert_shouldConvert() {
        PokemonSpeciesResponseBean source = random(PokemonSpeciesResponseBean.class);

        PokemonBean result = converter.convert(source);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("description", "habitat")
                .isEqualTo(source);
        assertThat(result.description())
                .isEqualTo(source.flavorTextEntries().get(0).flavorText().replaceAll("\\s", " "));
        assertThat(result.habitat())
                .isEqualTo(source.habitat().name());
    }
}
