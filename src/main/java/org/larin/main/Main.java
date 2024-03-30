package org.larin.main;

import org.larin.client.Client;
import org.larin.client.ClientGUI;
import org.larin.server.Server;
import org.larin.server.ServerGUI;


public class Main {

    public static void main(String[] args) {
        Server server = new Server(new ServerGUI());
        Client client = new Client(new ClientGUI());
        Client client1 = new Client(new ClientGUI());

    }
}