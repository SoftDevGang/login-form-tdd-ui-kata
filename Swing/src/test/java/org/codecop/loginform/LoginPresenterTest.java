package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.codecop.auth.AuthenticationResult;
import org.codecop.auth.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LoginPresenterTest {

    // --- enable/disable logic of Log in button

    LoginModel model = new LoginModel();
    LoginView view = mock(LoginView.class);
    AuthenticationService auth = mock(AuthenticationService.class);

    LoginPresenter presenter = new LoginPresenter(model, view, auth);

    @Test
    void shouldDisableLoginButtonForEmptyLookup() {
        model.setLoginButtonActive(true);

        presenter.lookupChanged("");

        assertFalse(model.getLoginButtonActive());
        verify(view).disableLogin();
    }

    @Test
    void shouldDisableLoginButtonForEmptyPassword() {
        model.setLoginButtonActive(true);

        presenter.passwordChanged("");

        assertFalse(model.getLoginButtonActive());
        verify(view).disableLogin();
    }

    @Test
    void shouldPassLookupAndPasswordToModel() {
        presenter.lookupChanged("user");
        presenter.passwordChanged("pass");

        assertEquals("user", model.getLookup());
        assertEquals("pass", model.getPassword());
    }

    @Test
    void shouldEnableLoginButtonloginForNonEmptyFields() {
        model.setLoginButtonActive(false);

        presenter.lookupChanged("Amanda");
        presenter.passwordChanged("secret123");

        assertTrue(model.getLoginButtonActive());
        verify(view).enableLogin();
    }

    @Test
    void shouldNotEnableLoginButtonForLookupOnly() {
        model.setLoginButtonActive(false);

        presenter.lookupChanged("Amanda");

        assertFalse(model.getLoginButtonActive());
    }

    @Test
    void shouldNotEnableLoginButtonForPasswordOnly() {
        model.setLoginButtonActive(false);

        presenter.passwordChanged("secret");

        assertFalse(model.getLoginButtonActive());
    }

    // --- login action

    @Test
    void shouldCloseViewOnSuccessLogin() {
        model.setLookup("user");
        model.setPassword("secret");

        when(auth.authenticate("user", "secret")).thenReturn(new AuthenticationResult(true, null));

        presenter.loginButtonClicked();

        verify(view).close();
    }

    @Test
    void shouldDisplayErrorOnFailedLogin() {
        model.setLookup("user2");
        model.setPassword("secret2");

        when(auth.authenticate("user2", "secret2")).thenReturn(new AuthenticationResult(false, "Login failed."));

        presenter.loginButtonClicked();

        verify(view).showError("Login failed.");
    }

    // ---

    @Test
    void shouldRegisterItselfToView() {
        // TODO capture listener, trigger each of them to test the wireing? 
        verify(view).registerLoginListener(Mockito.any(LoginListener.class));
    }

}

// test case: shouldDisableLoginButtonOnNewDialog() {
// test case: ? reset error message on success
