package org.codecop.loginform;

import java.awt.Color;

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

    // --- UI components

    public void testHasLoginButtonWithTextAndStyle() throws ComponentSearchException {
        showFrame((JPanel) view);

        JButton loginButton = findLoginButton();

        assertEquals("Log in", loginButton.getText());
        // colour dark blue
        assertEquals(Color.BLUE, loginButton.getBackground());
        assertEquals(Color.WHITE, loginButton.getForeground());
        assertTrue(loginButton.isOpaque());
        // disabled at start
        assertFalse(loginButton.isEnabled());
    }

    // --- methods in interface

    private JButton findLoginButton() throws ComponentSearchException {
        return (JButton) getFinder().find(new NameMatcher("LoginButton"));
    }

    public void testEnableDisableLoginButton() throws ComponentSearchException {
        showFrame((JPanel) view);

        JButton loginButton = findLoginButton();

        view.disableLogin();
        assertFalse(loginButton.isEnabled());

        view.enableLogin();
        assertTrue(loginButton.isEnabled());

        view.disableLogin();
        assertFalse(loginButton.isEnabled());
    }

}
