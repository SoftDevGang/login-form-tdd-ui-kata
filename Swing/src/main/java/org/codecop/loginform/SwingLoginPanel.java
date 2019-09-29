package org.codecop.loginform;

import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.codecop.swing.AllDocumentListener;

public class SwingLoginPanel extends JPanel implements LoginView {

    private static final Color ERROR_COLOR = new Color(255, 0, 0);

    private final JTextField lookupField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JLabel errorField = new JLabel();
    private final JButton loginButton = new JButton("Log in");

    public SwingLoginPanel() {
        setName("LoginPanel");
        setBackground(Color.WHITE);
        setForeground(new Color(0x333333));
        Color titleColor = new Color(0xcccccc);
        TitledBorder border = new TitledBorder(new LineBorder(titleColor, 2), "Login to Clean Code Center");
        border.setTitleColor(titleColor);
        setBorder(border);

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
        URL errorIcon = getClass().getClassLoader().getResource("icon-library.net_error-image-icon-21.png");
        errorField.setIcon(new ImageIcon(errorIcon));
        errorField.setText(message);
        errorField.setBorder(new LineBorder(ERROR_COLOR));
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
