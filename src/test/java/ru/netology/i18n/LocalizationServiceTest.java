package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceTest {

    @Test
    void localeRus() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }
    @Test
    void localUSA(){
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals("Welcome", localizationService.locale(Country.USA));
    }

}