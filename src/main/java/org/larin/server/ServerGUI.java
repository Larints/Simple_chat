package org.larin.server;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ServerGUI extends JFrame implements ServerView {

    private static final int WINDOWS_HEIGHT = 400;
    private static final int WINDOWS_WIDTH = 507;
    private static final int WINDOWS_POSX = 800;
    private static final int WINDOWS_POSY = 300;

    private JTextArea chatArea;
    private JScrollPane scrollPane;
    private JPanel bottomPanel;
    private JButton startButton, stopButton;

    private ServerModel serverModel;

    public ServerGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(WINDOWS_POSX, WINDOWS_POSY);
        setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        setTitle("Server");
        setMainPanel();
        setBottomPanel();
        setVisible(true);

    }

    private void setMainPanel() {
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        scrollPane = new JScrollPane();
        add(chatArea, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.WEST);
    }

    private void setBottomPanel() {
        bottomPanel = new JPanel(new GridLayout(1, 2));
        startButton = new JButton("Start");
        startButtonClick(startButton);
        stopButton = new JButton("Stop");
        stopButtonClick(stopButton);
        bottomPanel.add(startButton);
        bottomPanel.add(stopButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void startButtonClick(JButton button) {
        button.addActionListener(e -> {
            chatArea.setText("");
            chatArea.append("Добро пожаловать. Сервер запущен " +
                    LocalDate.now() + "\n");
            start();
        });
    }

    private void stopButtonClick(JButton button) {
        button.addActionListener(e -> {
            chatArea.setText("Сервер выключен. Всего хорошего!");
            serverModel.stop();
        });
    }

    public void updateChatArea(String message) {
        this.chatArea.append(message);
    }

    @Override
    public void start() {
        serverModel.runServer();
    }

    @Override
    public void stop() {
        serverModel.stop();
    }

    public void setServerModel(ServerModel serverModel) {
        this.serverModel = serverModel;
    }
}
