package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.assertFalse;

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

}
