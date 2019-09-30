package org.codecop.loginform;

import javax.swing.JFrame;

import org.codecop.auth.AuthenticationResult;
import org.codecop.auth.AuthenticationService;

public class MainToSeePanel {

    public static void main(String[] args) {
        LoginModel model = new LoginModel();

        SwingLoginPanel view = new SwingLoginPanel();

        AuthenticationService untrustingAuthenticationService = new AuthenticationService() {
            @SuppressWarnings("unused")
            @Override
            public AuthenticationResult authenticate(String phoneEmailUserName, String password) {
                return new AuthenticationResult(false, "No access!");
            }

            @SuppressWarnings("unused")
            @Override
            public void rememberMe(String phoneEmailUserName) {
                //
            }
        };

        new LoginPresenter(model, view, untrustingAuthenticationService);

        JFrame frame = new JFrame("Visual Inspection");
        frame.getContentPane().add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
