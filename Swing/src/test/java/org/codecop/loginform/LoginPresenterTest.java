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

class LoginPresenterTest {

    // --- enable/disable logic of Log in button

    LoginModel model = new LoginModel();
    LoginView view = mock(LoginView.class);
    LoginPresenter presenter = new LoginPresenter(model, view);

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

    AuthenticationService auth = mock(AuthenticationService.class);

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
        model.setLookup("user");
        model.setPassword("secret");

        when(auth.authenticate("user", "secret")).thenReturn(new AuthenticationResult(false, "Login failed."));

        presenter.loginButtonClicked();

        assertEquals("Login failed.", model.getErrorMessage());
        verify(view).showError("Login failed.");
    }

}
