package org.codecop.loginform;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SwingLoginPanel extends JPanel implements LoginView {

    JButton loginButton = new JButton();

    public SwingLoginPanel() {
        loginButton.setName("LoginButton");
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

}
