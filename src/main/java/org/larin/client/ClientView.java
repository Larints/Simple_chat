package org.larin.client;

public interface ClientView {

    void sendMessage();

    String getChatText();

    void updateChatText(String chatText);

    String getIpAddress();

    String getPort();
}
