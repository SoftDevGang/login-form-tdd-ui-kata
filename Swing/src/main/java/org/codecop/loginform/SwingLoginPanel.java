package org.codecop.loginform;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.codecop.swing.AllDocumentListener;

public class SwingLoginPanel extends JPanel implements LoginView {

    private static final Color ERROR_COLOR = new Color(255, 0, 0);

    private final JTextField lookupField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JLabel errorField = new JLabel();
    private final JButton loginButton = new JButton("Log in");

    public SwingLoginPanel() {
        createLookupField();
        createPasswordField();
        createErrorField();
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

    private void createErrorField() {
        errorField.setName("ErrorField");
        errorField.setForeground(ERROR_COLOR);
        add(errorField);
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
        SwingUtilities.invokeLater(() -> errorField.setText(message));
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
