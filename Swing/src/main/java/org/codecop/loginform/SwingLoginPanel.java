package org.codecop.loginform;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SwingLoginPanel extends JPanel implements LoginView {

    JButton loginButton = new JButton("Log in");

    public SwingLoginPanel() {
        createLoginButton();
    }

    private void createLoginButton() {
        loginButton.setName("LoginButton");
        loginButton.setOpaque(true);
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        loginButton.setEnabled(false);
        add(loginButton);
    }

    @Override
    public void enableLogin() {
        loginButton.setEnabled(true);
    }

    @Override
    public void disableLogin() {
        loginButton.setEnabled(false);
    }

    @Override
    public void close() {
    }

    @Override
    public void showError(String message) {
    }

    @Override
    public void registerLoginListener(LoginListener listener) {
        loginButton.addActionListener(ae -> listener.loginButtonClicked());
    }

}
