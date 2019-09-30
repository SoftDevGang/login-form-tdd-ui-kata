package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

    // --- login action

    @Test
    void shouldCloseViewOnSuccessLoginAsynchronously() throws InterruptedException {
        model.setLookup("user");
        model.setPassword("secret");
        
        CountDownLatch viewCloseCalled = new CountDownLatch(1);
        doAnswer(invocation -> {
            viewCloseCalled.countDown();
            return null;
        }).when(view).close();

        CountDownLatch authenticationFinished = new CountDownLatch(1);
        when(auth.authenticate("user", "secret")).thenAnswer(invocation -> {
            authenticationFinished.await();
            return new AuthenticationResult(true, null);
        });

        presenter.loginButtonClicked();
        
        authenticationFinished.countDown();
        viewCloseCalled.await(1, TimeUnit.SECONDS);
        verify(view).close();
    }

    @Test
    void shouldDisplayErrorOnFailedLoginAsynchronously() throws InterruptedException {
        model.setLookup("user2");
        model.setPassword("secret2");

        CountDownLatch viewShowErrorCalled = new CountDownLatch(1);
        doAnswer(invocation -> {
            viewShowErrorCalled.countDown();
            return null;
        }).when(view).showError("Login failed.");

        CountDownLatch authenticationFinished = new CountDownLatch(1);
        when(auth.authenticate("user2", "secret2")).thenAnswer(invocation -> {
            authenticationFinished.await();
            return new AuthenticationResult(false, "Login failed.");
        });
        
        presenter.loginButtonClicked();

        authenticationFinished.countDown();
        viewShowErrorCalled.await(1, TimeUnit.SECONDS);
        verify(view).showError("Login failed.");
    }

    // ---

    @Test
    void shouldRegisterItselfToView() throws InterruptedException {
        // capture listener, trigger each of them to test the wiring 
        ArgumentCaptor<LoginListener> argument = ArgumentCaptor.forClass(LoginListener.class);
        verify(view).registerLoginListener(argument.capture());
        LoginListener listener = argument.getValue();

        listener.lookupChanged("user");
        assertEquals("user", model.getLookup());
        listener.passwordChanged("pass");
        assertEquals("pass", model.getPassword());
        listener.loginButtonClicked();
        Thread.sleep(10);
        verify(auth).authenticate("user", "pass");
    }

}
