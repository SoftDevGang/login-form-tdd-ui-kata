package org.codecop.loginform;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SwingLoginPanel extends JPanel implements LoginView {

    JButton loginButton = new JButton("Log in");

    public SwingLoginPanel() {
        createLoginButton();
    }

    private void createLoginButton() {
        loginButton.setName("LoginButton");
        add(loginButton);
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
