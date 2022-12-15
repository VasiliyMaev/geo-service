package ru.netology.sender;

import ru.netology.entity.Country;

import java.util.Map;

public interface MessageSender {

    String send(Map<String, String> headers);
}
