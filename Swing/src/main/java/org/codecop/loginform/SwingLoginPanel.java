package org.codecop.loginform;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.codecop.swing.AllDocumentListener;

public class SwingLoginPanel extends JPanel implements LoginView {

    JTextField lookupField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JButton loginButton = new JButton("Log in");

    public SwingLoginPanel() {
        createLookupField();
        createPasswordField();
        createLoginButton();
    }

    private void createLookupField() {
        lookupField.setName("LookupField");

        add(lookupField);
    }

    private void createPasswordField() {
        passwordField.setName("PasswordField");

        add(passwordField);
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
        lookupField.getDocument().addDocumentListener(new AllDocumentListener() {
            @Override
            protected void fire() {
                listener.lookupChanged(lookupField.getText());
            }
        });

        passwordField.getDocument().addDocumentListener(new AllDocumentListener() {
            @Override
            protected void fire() {
                listener.passwordChanged(new String(passwordField.getPassword()));
            }
        });

        loginButton.addActionListener(ae -> listener.loginButtonClicked());
    }

}
