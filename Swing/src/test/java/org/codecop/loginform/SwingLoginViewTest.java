package org.codecop.loginform;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import abbot.finder.ComponentSearchException;
import abbot.finder.matchers.NameMatcher;
import abbot.tester.JButtonTester;
import abbot.tester.JTextFieldTester;
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
        LoginListener listener = mock(LoginListener.class);
        view.enableLogin();
        view.registerLoginListener(listener);

        JButton loginButton = findLoginButton();
        JButtonTester tester = new JButtonTester();
        tester.actionClick(loginButton);

        verify(listener).loginButtonClicked();
    }

    // --- input fields

    public void testHasLookupFieldsWithStyle() throws ComponentSearchException {
        LoginListener listener = mock(LoginListener.class);
        view.registerLoginListener(listener);

        showFrame((JPanel) view);
        JTextField lookupField = findLookupField();

        assertEquals(20, lookupField.getColumns());

        JTextFieldTester tester = new JTextFieldTester();
        tester.actionEnterText(lookupField, "user");

        verify(listener).lookupChanged("user");
    }

    private JTextField findLookupField() throws ComponentSearchException {
        return (JTextField) getFinder().find(new NameMatcher("LookupField"));
    }

    public void testHasPasswordFieldsWithStyle() throws ComponentSearchException {
        LoginListener listener = mock(LoginListener.class);
        view.registerLoginListener(listener);

        showFrame((JPanel) view);
        JPasswordField passwordField = findPasswordField();

        assertEquals(20, passwordField.getColumns());

        JTextFieldTester tester = new JTextFieldTester();
        tester.actionEnterText(passwordField, "secret");

        verify(listener).passwordChanged("secret");
    }

    private JPasswordField findPasswordField() throws ComponentSearchException {
        return (JPasswordField) getFinder().find(new NameMatcher("PasswordField"));
    }

}
