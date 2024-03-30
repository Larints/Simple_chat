package org.larin.client;

import org.larin.server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements ClientModel {

    private String login;

    private String password;

    private final ClientGUI clientGUI;

    private boolean isAuthenticated;

    private Socket socket;

    private BufferedReader bufferedReader;

    private BufferedWriter bufferedWriter;

    private String name;

    private static final int PORT = 8181;


    public Client(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;
        clientGUI.setClientModel(this);
        try {
            this.socket = new Socket("localhost", PORT);
        } catch (IOException e) {
            clientGUI.updateChatText("Error connecting");
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            clientGUI.updateChatText(e.getMessage());
        }
    }


    @Override
    public void sentDate() {
        listenForMessage();
    }


    @Override
    public void sendMessage(String message) {
        try {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            this.clientGUI.updateChatText(name);
            while (socket.isConnected()) {
                bufferedWriter.write(name + " " + message);
                this.clientGUI.updateChatText(name + " " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            this.clientGUI.updateChatText(e.getMessage());
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenForMessage() {
        new Thread(() -> {
            String messageFromGroup;
            while (socket.isConnected()) {
                try {
                    messageFromGroup = bufferedReader.readLine();
                    clientGUI.updateChatText(messageFromGroup);
                } catch (IOException e) {
                    clientGUI.updateChatText(e.getMessage());
                    closeEverything(socket, bufferedReader, bufferedWriter);

                }
            }
        }).start();
    }


    @Override
    public String getChatText() {
        return null;
    }

    @Override
    public void sendPrivateMessage(String message, String username) {

    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
}
