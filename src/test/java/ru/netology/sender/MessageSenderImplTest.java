package ru.netology.sender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class MessageSenderImplTest {
    MessageSenderImpl messageSenderImpl;
    @Mock
    private GeoService geoService;
    @Mock
    private LocalizationService localizationService;


    @BeforeEach
    void init() {
        messageSenderImpl = new MessageSenderImpl(geoService, localizationService);

    }

    @Test
    public void testSendInRus() {

        LocalizationService localizationService = spy(new LocalizationServiceImpl());
        GeoService geoServiceMock = mock(GeoServiceImpl.class);
        when(geoServiceMock.byIp(anyString())).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "1");

        String message = messageSender.send(headers);
        verify(geoServiceMock, only()).byIp("1");
        verify(localizationService, atLeastOnce()).locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", message);
    }

    @Test
    public void testSendInEng() {

        LocalizationService localizationService = spy(new LocalizationServiceImpl());
        GeoService geoServiceMock = mock(GeoServiceImpl.class);
        when(geoServiceMock.byIp(anyString())).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "1");

        String message = messageSender.send(headers);
        verify(geoServiceMock, only()).byIp("1");
        verify(localizationService, atLeastOnce()).locale(Country.USA);
        assertEquals("Welcome", message);
    }

}