package org.codecop.loginform;

import javax.swing.JButton;
import javax.swing.JPanel;

import abbot.finder.ComponentSearchException;
import abbot.finder.matchers.NameMatcher;
import junit.extensions.abbot.ComponentTestFixture;

public class SwingLoginViewTest extends ComponentTestFixture {

    LoginView view = new SwingLoginPanel();

    public SwingLoginViewTest(String name) {
        super(name);
    }

    // --- methods in interface

    public void testEnableDisableLoginButton() throws ComponentSearchException {
        showFrame((JPanel) view);

        JButton loginButton = (JButton) getFinder().find(new NameMatcher("LoginButton"));

        view.disableLogin();
        assertFalse(loginButton.isEnabled());

        view.enableLogin();
        assertTrue(loginButton.isEnabled());

        view.disableLogin();
        assertFalse(loginButton.isEnabled());
    }

}
