package com.truelayer.pokedex.service.client.funtranslations;

import com.truelayer.pokedex.service.client.funtranslations.bean.TranslationRequestBean;
import com.truelayer.pokedex.service.client.funtranslations.bean.TranslationResponseBean;
import com.truelayer.pokedex.service.client.funtranslations.enums.CharacterEnum;
import com.truelayer.pokedex.service.client.funtranslations.exception.FunTranslationsException;
import com.truelayer.pokedex.service.client.funtranslations.exception.FunTranslationsTooManyRequestsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class FunTranslationsService {
    private static final Logger logger = LoggerFactory.getLogger(FunTranslationsService.class);

    @Autowired private FunTranslationsClient funTranslationsClient;

    public String getTranslation(String text, CharacterEnum character) {
        logger.info("Calling FunTranslations API to translate the text \"{}\" using the {} translation method.", text, character.toString());
        TranslationRequestBean translationRequestBean = TranslationRequestBean.builder()
                .text(text)
                .build();
        try {
            TranslationResponseBean translationResponseBean = funTranslationsClient.getTranslation(character.toString(), translationRequestBean);
            return Optional.ofNullable(translationResponseBean.contentBean())
                    .map(TranslationResponseBean.TranslationContentBean::translated)
                    .orElse(null);
        }
        catch (FunTranslationsTooManyRequestsException e) {
            logger.warn("Request limit to FunTranslations API exceeded. Please slow down and try again later");
            return null;
        }
        catch (FunTranslationsException e) {
            logger.error("An error occurred while communicating with the FunTranslations API");
            return null;
        }
    }

}

