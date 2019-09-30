package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.codecop.auth.AuthenticationResult;
import org.codecop.auth.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

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

    @Test
    void shouldDisplayErrorOnFailedLogin() {
        model.setLookup("user2");
        model.setPassword("secret2");

        when(auth.authenticate("user2", "secret2")).thenReturn(new AuthenticationResult(false, "Login failed."));

        presenter.loginButtonClicked();

        verify(view).showError("Login failed.");
    }

    @Test
    void shouldRegisterItselfToView() {
        when(auth.authenticate(any(String.class), any(String.class))).thenReturn(new AuthenticationResult(true, null));

        ArgumentCaptor<LoginListener> argument = ArgumentCaptor.forClass(LoginListener.class);
        verify(view).registerLoginListener(argument.capture());
        LoginListener listener = argument.getValue();

        listener.lookupChanged("user");
        assertEquals("user", model.getLookup());
        listener.passwordChanged("pass");
        assertEquals("pass", model.getPassword());
        listener.loginButtonClicked();
        verify(auth).authenticate("user", "pass");
    }

}
