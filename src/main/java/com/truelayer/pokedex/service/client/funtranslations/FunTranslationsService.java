package com.truelayer.pokedex.service.client.funtranslations;

import com.truelayer.pokedex.service.client.funtranslations.bean.TranslationRequestBean;
import com.truelayer.pokedex.service.client.funtranslations.bean.TranslationResponseBean;
import com.truelayer.pokedex.service.client.funtranslations.enums.CharacterEnum;
import com.truelayer.pokedex.service.client.funtranslations.exception.FunTranslationsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FunTranslationsService {
    private static final Logger logger = LoggerFactory.getLogger(FunTranslationsService.class);

    @Autowired private FunTranslationsClient funtranslationsClient;

    public String getTranslation(String text, CharacterEnum character) {
        logger.info("Call FunTranslations to translate \"{}\" using {} translation", text, character.toString());
        TranslationRequestBean translationRequestBean = TranslationRequestBean.builder()
                .text(text)
                .build();
        try {
            TranslationResponseBean translationResponseBean = funtranslationsClient.getTranslation(character.toString(), translationRequestBean);
            return translationResponseBean.contentBean().translated();
        }
        catch (FunTranslationsException e) {
            return null;
        }
    }

}

