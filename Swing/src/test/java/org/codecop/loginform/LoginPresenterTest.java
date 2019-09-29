package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.codecop.auth.AuthenticationResult;
import org.codecop.auth.AuthenticationService;
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

    // --- login action

    AuthenticationService auth = mock(AuthenticationService.class);

    @Test
    void shouldCloseViewOnSuccessLogin() {
        model.setLookup("user");
        model.setPassword("secret");

        when(auth.authenticate("user", "secret")).thenReturn(new AuthenticationResult(true, null));

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.loginButtonClicked();

        verify(view).close();
    }

}
