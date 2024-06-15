package com.truelayer.pokedex.service.client.funtranslations;

import com.truelayer.pokedex.service.client.funtranslations.bean.TranslationRequestBean;
import com.truelayer.pokedex.service.client.funtranslations.bean.TranslationResponseBean;
import com.truelayer.pokedex.service.client.funtranslations.configuration.FunTranslationsClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@FeignClient(name = "funTranslationsClient", url = "${funtranslations.base.url}", configuration = FunTranslationsClientConfiguration.class)
public interface FunTranslationsClient {
        @RequestMapping(method = POST, path = "/translate/{character}", produces = MediaType.APPLICATION_JSON_VALUE)
        TranslationResponseBean getTranslation(@PathVariable("character") String character, @RequestBody TranslationRequestBean requestBean);
}