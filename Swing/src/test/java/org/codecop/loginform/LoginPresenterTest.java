package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LoginPresenterTest {

    @Test
    void loginButtonGetsDisabledForEmptyLookup() {
        LoginModel model = new LoginModel();
        model.setLoginButtonActive(true);

        LoginPresenter presenter = new LoginPresenter(model);

        presenter.lookupChanged("");

        assertFalse(model.getLoginButtonActive());
    }

    @Test
    void loginButtonGetsEnabledForEmptyPassword() {
        LoginModel model = new LoginModel();
        model.setLoginButtonActive(true);

        LoginPresenter presenter = new LoginPresenter(model);

        presenter.passwordChanged("");

        assertFalse(model.getLoginButtonActive());
    }

    @Test
    void shouldPassLookupAndPasswordToModel() {
        LoginModel model = new LoginModel();

        LoginPresenter presenter = new LoginPresenter(model);

        presenter.lookupChanged("user");
        presenter.passwordChanged("pass");

        assertEquals("user", model.getLookup());
        assertEquals("pass", model.getPassword());
    }

    @Test
    void loginButtonGetsEnabledForNonEmptyFields() {
        LoginModel model = new LoginModel();
        model.setLoginButtonActive(false);

        LoginPresenter presenter = new LoginPresenter(model);

        presenter.lookupChanged("Amanda");
        presenter.passwordChanged("secret123");

        assertTrue(model.getLoginButtonActive());
    }

}

// not enabled when one is not empty, other stays empty
