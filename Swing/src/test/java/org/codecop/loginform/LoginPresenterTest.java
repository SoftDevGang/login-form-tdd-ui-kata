package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class LoginPresenterTest {

    LoginModel model = new LoginModel();
    LoginView view = mock(LoginView.class);

    @Test
    void shouldPassLookupAndPasswordToModel() {
        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.lookupChanged("user");
        presenter.passwordChanged("pass");

        assertEquals("user", model.getLookup());
        assertEquals("pass", model.getPassword());
    }

}
