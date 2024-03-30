package org.larin.server;

import org.larin.client.Client;
import org.larin.client.ClientGUI;
import org.larin.client_manager.ClientManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server implements ServerModel {


    private ServerGUI serverGUI;

    private final static int PORT = 8181;

    private final ServerSocket serverSocket;


    public Server(ServerGUI serverGUI) {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.serverGUI = serverGUI;
        this.serverGUI.setServerModel(this);
    }

    public void runServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Запустились");
                this.serverGUI.updateChatArea("Подключился новый собеседник!");
                ClientManager client = new ClientManager(socket);
                Thread thread = new Thread(client);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Не запустились");
        }
    }

    public void closeSocket() {
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            serverGUI.updateChatArea(e.getMessage());
        }
    }


    @Override
    public void stop() {

    }

    @Override
    public boolean getStatus() {
        return false;
    }

    @Override
    public String getLog() {
        return null;
    }

    @Override
    public void receivingMessage(String message) {

    }

    @Override
    public void addClient(Client client) {

    }

    @Override
    public void setStatus(boolean status) {

    }


}

