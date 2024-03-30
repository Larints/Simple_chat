package org.larin.client;

import java.net.Socket;

public interface ClientModel {


    void sendMessage(String message);

    String getChatText();

    void sendPrivateMessage(String message, String username);

    void setLogin(String login);

    void setPassword(String password);

    void sentDate();
}
