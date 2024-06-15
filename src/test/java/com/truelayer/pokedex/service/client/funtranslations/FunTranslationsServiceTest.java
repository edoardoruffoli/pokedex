package com.truelayer.pokedex.service.client.funtranslations;

import com.truelayer.pokedex.BaseUnitTest;
import com.truelayer.pokedex.service.client.funtranslations.bean.TranslationRequestBean;
import com.truelayer.pokedex.service.client.funtranslations.bean.TranslationResponseBean;
import com.truelayer.pokedex.service.client.funtranslations.enums.CharacterEnum;
import com.truelayer.pokedex.service.client.funtranslations.exception.FunTranslationsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class FunTranslationsServiceTest extends BaseUnitTest {

    @Mock private FunTranslationsClient funTranslationsClient;
    @InjectMocks
    private FunTranslationsService service;


    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(
                funTranslationsClient);
    }

    @Test
    void getTranslation_shouldGet() {
        // given
        String text = random(String.class);
        CharacterEnum character = random(CharacterEnum.class);
        TranslationResponseBean translationResponseBean = random(TranslationResponseBean.class);

        when(funTranslationsClient.getTranslation(any(), any())).thenReturn(translationResponseBean);

        // when
        String res = service.getTranslation(text, character);

        // verify
        assertThat(res)
                .isEqualTo(translationResponseBean.contentBean().translated());
        verify(funTranslationsClient).getTranslation(character.toString(), TranslationRequestBean.builder()
                .text(text)
                .build());
    }

    @Test
    void getTranslation_shouldNotGet() {
        // given
        String text = random(String.class);
        CharacterEnum character = random(CharacterEnum.class);

        doThrow(FunTranslationsException.class).when(funTranslationsClient).getTranslation(any(), any());

        // when
        String res = service.getTranslation(text, character);

        // verify
        assertThat(res)
                .isEqualTo(null);
        verify(funTranslationsClient).getTranslation(character.toString(), TranslationRequestBean.builder()
                .text(text)
                .build());
    }

}