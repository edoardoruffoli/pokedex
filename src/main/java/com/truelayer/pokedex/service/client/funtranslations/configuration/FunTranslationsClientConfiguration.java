package com.truelayer.pokedex.service.client.funtranslations.configuration;

import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FunTranslationsClientConfiguration {
    public static final int RETRY_INITIAL_INTERVAL = 100;
    public static final long RETRY_MAX_INTERVAL = SECONDS.toMillis(1);
    public static final int RETRY_MAX_ATTEMPTS = 5;
    public static final Logger.Level LOGGER_LEVEL = Logger.Level.FULL;

    @Bean
    public Logger.Level feignLoggerLevel() {
        return LOGGER_LEVEL;
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(RETRY_INITIAL_INTERVAL, RETRY_MAX_INTERVAL, RETRY_MAX_ATTEMPTS);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FunTranslationsErrorDecoder();
    }

}
