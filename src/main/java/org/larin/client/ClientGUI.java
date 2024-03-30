package org.larin.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ClientView {

    private static final int WINDOWS_HEIGHT = 400;
    private static final int WINDOWS_WIDTH = 507;
    private static final int WINDOWS_POSX = 800;
    private static final int WINDOWS_POSY = 300;


    private JPanel topPanel, addressPanel, authenticationPanel, bottomPanel;
    private JTextField addressField, portField, loginField, sendField;
    private JPasswordField passwordField;
    private JButton loginButton, sendButton;
    private JTextArea chat;
    private JScrollPane scrollPanel;

    private ClientModel clientModel;

    public ClientGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(WINDOWS_POSX, WINDOWS_POSY);
        setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        setTitle("Client");
        setTopPanel();
        setChat();
        setScrollArea();
        setBottomPanel();
        setVisible(true);
    }

    private void setTopPanel() {
        topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(setAddressPanel());
        topPanel.add(setAuthenticationPanel());
        add(topPanel, BorderLayout.NORTH);
    }

    private JPanel setAddressPanel() {
        addressPanel = new JPanel(new GridLayout(1, 2));
        addressPanel.add(addressField = new JTextField("localhost"));
        addressPanel.add(portField = new JTextField("8181"));
        addressPanel.add(new JPanel());
        add(addressPanel, BorderLayout.NORTH);
        return addressPanel;
    }

    private JPanel setAuthenticationPanel() {
        authenticationPanel = new JPanel(new GridLayout(1, 3));
        loginField = new JTextField("Login");
        passwordField = new JPasswordField("Password");
        passwordField.setEchoChar('*');
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            this.clientModel.setLogin(this.loginField.getText());
            this.clientModel.setPassword(this.passwordField.getText());
            this.clientModel.sentDate();
        });
        authenticationPanel.add(loginField);
        authenticationPanel.add(passwordField);
        authenticationPanel.add(loginButton);
        add(authenticationPanel, BorderLayout.NORTH);
        return authenticationPanel;
    }


    private void setChat() {
        chat = new JTextArea();
        chat.setLineWrap(true);
        chat.setEditable(false);
        add(chat, BorderLayout.CENTER);
    }

    private void setScrollArea() {
        scrollPanel = new JScrollPane();
        add(scrollPanel, BorderLayout.EAST);
    }

    private void setBottomPanel() {
        bottomPanel = new JPanel(new GridLayout(1, 2));
        sendField = new JTextField(" ");
        sendField.setSize(50, 50);
        sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());
        bottomPanel.add(sendField);
        bottomPanel.add(sendButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void sendMessage() {
        clientModel.sendMessage(sendField.getText());
    }


    @Override
    public String getChatText() {
        return chat.getText();
    }


    @Override
    public void updateChatText(String chatText) {
        this.chat.append(chatText);
    }

    @Override
    public String getIpAddress() {
        return addressField.getText();
    }

    @Override
    public String getPort() {
        return portField.getText();
    }

    public void setClientModel(ClientModel clientModel) {
        this.clientModel = clientModel;
    }

    public String getName() {
        return loginField.getText();
    }
}
