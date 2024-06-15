package com.truelayer.pokedex.controller.pokemon.converter;

import com.truelayer.pokedex.BaseUnitTest;
import com.truelayer.pokedex.controller.pokemon.bean.PokemonResponseBean;
import com.truelayer.pokedex.service.pokemon.bean.PokemonBean;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PokemonBeanToResponseConverterTest extends BaseUnitTest {

    @InjectMocks PokemonBeanToResponseConverter converter;

    @Test
    public void convert_shouldConvert() {
        PokemonBean source = random(PokemonBean.class);

        PokemonResponseBean result = converter.convert(source);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(source);
    }
}
