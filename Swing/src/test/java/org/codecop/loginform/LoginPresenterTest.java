package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class LoginPresenterTest {

    // --- enable/disable logic of Log in button

    LoginModel model = new LoginModel();
    LoginView view = mock(LoginView.class);

    @Test
    void shouldDisableLoginButtonForEmptyLookup() {
        model.setLoginButtonActive(true);

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.lookupChanged("");

        assertFalse(model.getLoginButtonActive());
        verify(view).disableLogin();
    }

    @Test
    void shouldDisableLoginButtonForEmptyPassword() {
        model.setLoginButtonActive(true);

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.passwordChanged("");

        assertFalse(model.getLoginButtonActive());
        // TODO verify(view).disableLogin();
    }

    @Test
    void shouldPassLookupAndPasswordToModel() {
        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.lookupChanged("user");
        presenter.passwordChanged("pass");

        assertEquals("user", model.getLookup());
        assertEquals("pass", model.getPassword());
    }

    @Test
    void shouldEnableLoginButtonloginForNonEmptyFields() {
        model.setLoginButtonActive(false);

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.lookupChanged("Amanda");
        presenter.passwordChanged("secret123");

        assertTrue(model.getLoginButtonActive());
        // TODO verify(view).enableLogin();
    }

    @Test
    void shouldNotEnableLoginButtonForLookupOnly() {
        model.setLoginButtonActive(false);

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.lookupChanged("Amanda");

        assertFalse(model.getLoginButtonActive());
    }

    @Test
    void shouldNotEnableLoginButtonForPasswordOnly() {
        model.setLoginButtonActive(false);

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.passwordChanged("secret");

        assertFalse(model.getLoginButtonActive());
    }

}
