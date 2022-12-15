package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceTest {

    @Test
    void byIpLocalhost() {
        Location location = new GeoServiceImpl().byIp("127.0.0.1");
        assertNull(location.getCountry());
        assertNull(location.getCity());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuilding());
    }
    @Test
    void byIpRus() {
        Location location = new GeoServiceImpl().byIp("172.0.32.11");
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Moscow", location.getCity());
        assertEquals("Lenina", location.getStreet());
        assertEquals(15, location.getBuilding());
    }

    @Test
    void byIpUSA(){
        Location location = new GeoServiceImpl().byIp("96.44.183.149");
        assertEquals(Country.USA, location.getCountry());
        assertEquals("New York", location.getCity());
        assertEquals("10th Avenue", location.getStreet());
        assertEquals(32, location.getBuilding());
    }
}