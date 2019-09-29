package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.codecop.auth.AuthenticationResult;
import org.codecop.auth.AuthenticationService;
import org.junit.jupiter.api.Test;

class LoginPresenterTest {

    LoginModel model = new LoginModel();
    LoginView view = mock(LoginView.class);
    AuthenticationService auth = mock(AuthenticationService.class);

    LoginPresenter presenter = new LoginPresenter(model, view, auth);

    @Test
    void shouldPassLookupAndPasswordToModel() {
        presenter.lookupChanged("user");
        presenter.passwordChanged("pass");

        assertEquals("user", model.getLookup());
        assertEquals("pass", model.getPassword());
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

}
