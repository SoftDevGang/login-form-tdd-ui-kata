package org.codecop.loginform;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import abbot.finder.ComponentSearchException;
import abbot.finder.matchers.NameMatcher;
import abbot.tester.JButtonTester;
import junit.extensions.abbot.ComponentTestFixture;

public class SwingLoginViewTest extends ComponentTestFixture {

    LoginView view = new SwingLoginPanel();

    public SwingLoginViewTest(String name) {
        super(name);
    }

    // --- login button

    public void testHasLoginButtonWithTextAndStyle() throws ComponentSearchException {
        JButton loginButton = findLoginButton();

        assertEquals("Log in", loginButton.getText());
        // colour dark blue
        assertEquals(Color.BLUE, loginButton.getBackground());
        assertEquals(Color.WHITE, loginButton.getForeground());
        assertTrue(loginButton.isOpaque());
        // disabled at start
        assertFalse(loginButton.isEnabled());
    }

    private JButton findLoginButton() throws ComponentSearchException {
        showFrame((JPanel) view);
        return (JButton) getFinder().find(new NameMatcher("LoginButton"));
    }

    public void testEnableDisableLoginButton() throws ComponentSearchException {
        JButton loginButton = findLoginButton();

        view.disableLogin();
        assertFalse(loginButton.isEnabled());

        view.enableLogin();
        assertTrue(loginButton.isEnabled());

        view.disableLogin();
        assertFalse(loginButton.isEnabled());
    }

    public void testSendButtonClickToPresenter() throws ComponentSearchException {
        LoginButtonListener listener = mock(LoginButtonListener.class);
        view.enableLogin();
        view.registerLoginButtonListener(listener);

        JButton loginButton = findLoginButton();
        JButtonTester tester = new JButtonTester();
        tester.actionClick(loginButton);

        verify(listener).loginButtonClicked();
    }
}
