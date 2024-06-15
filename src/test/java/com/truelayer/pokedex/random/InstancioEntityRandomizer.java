package com.truelayer.pokedex.random;

import org.instancio.Instancio;
import org.instancio.InstancioOfClassApi;
import org.instancio.Select;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static org.instancio.Select.allLongs;

public class InstancioEntityRandomizer {

    public <T> T random(Class<T> randomClass, String... excludedFields) {
        InstancioOfClassApi<T> random = Instancio.of(randomClass);

        return Optional.ofNullable(excludedFields)
                .map(fields -> Arrays.stream(fields).map(Select::field))
                .orElseGet(Stream::empty)
                .map(random::ignore)
                .reduce((first, second) -> second)
                .orElse(random)
                .generate(allLongs(), gen -> gen.longs().range(0L, 10000000L))
                .lenient()
                .create();
    }

}
