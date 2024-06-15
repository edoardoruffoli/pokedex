package com.truelayer.pokedex;


import com.truelayer.pokedex.random.InstancioEntityRandomizer;

public abstract class BaseTest {

    private final InstancioEntityRandomizer entityRandomizer = new InstancioEntityRandomizer();

    protected <T> T random(Class<T> randomClass, String... excludedFields) {
        return entityRandomizer.random(randomClass, excludedFields);
    }

}
