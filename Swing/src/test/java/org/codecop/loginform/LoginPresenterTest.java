package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LoginPresenterTest {

    @Test
    void shouldPassLookupAndPasswordToModel() {
        LoginModel model = new LoginModel();

        LoginPresenter presenter = new LoginPresenter(model);

        presenter.lookupChanged("user");
        presenter.passwordChanged("pass");

        assertEquals("user", model.getLookup());
        assertEquals("pass", model.getPassword());
    }

}
