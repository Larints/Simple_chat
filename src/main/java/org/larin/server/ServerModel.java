package org.larin.server;

import org.larin.client.Client;

public interface ServerModel {

        void runServer();

        void stop();

        boolean getStatus();

        String getLog();

        void receivingMessage(String message);

        void addClient(Client client);

        void setStatus(boolean status);
}
