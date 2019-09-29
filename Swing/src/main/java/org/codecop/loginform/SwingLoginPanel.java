package org.codecop.loginform;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
        lookupField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(@SuppressWarnings("unused") DocumentEvent e) {
                fire();
            }

            @Override
            public void insertUpdate(@SuppressWarnings("unused") DocumentEvent e) {
                fire();
            }

            @Override
            public void changedUpdate(@SuppressWarnings("unused") DocumentEvent e) {
                fire();
            }

            private void fire() {
                listener.lookupChanged(lookupField.getText());
            }
        });

        passwordField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(@SuppressWarnings("unused") DocumentEvent e) {
                fire();
            }

            @Override
            public void insertUpdate(@SuppressWarnings("unused") DocumentEvent e) {
                fire();
            }

            @Override
            public void changedUpdate(@SuppressWarnings("unused") DocumentEvent e) {
                fire();
            }

            private void fire() {
                listener.passwordChanged(new String(passwordField.getPassword()));
            }
        });

        loginButton.addActionListener(ae -> listener.loginButtonClicked());
    }

}
