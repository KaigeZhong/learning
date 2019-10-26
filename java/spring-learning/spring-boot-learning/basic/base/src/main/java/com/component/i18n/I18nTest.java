package com.component.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class I18nTest {

    @Autowired
    public I18nTest(MessageSource messageSource) {
        System.out.println(messageSource.getMessage("ms", null, Locale.CHINESE));
    }
}
