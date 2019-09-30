package org.codecop.loginform;

import org.codecop.auth.AuthenticationResult;
import org.codecop.auth.AuthenticationService;

public class LoginPresenter {

    private final LoginModel model;
    private final LoginView view;
    private final AuthenticationService authenticationService;

    public LoginPresenter(LoginModel model, LoginView view, AuthenticationService authenticationService) {
        this.model = model;
        this.view = view;
        this.authenticationService = authenticationService;
        view.registerLoginListener(new LoginListener() {

            @Override
            public void lookupChanged(String lookup) {
                LoginPresenter.this.lookupChanged(lookup);
            }

            @Override
            public void passwordChanged(String password) {
                LoginPresenter.this.passwordChanged(password);
            }

            @Override
            public void loginButtonClicked() {
                LoginPresenter.this.loginButtonClicked();
            }
        });
    }

    public void lookupChanged(String newLookup) {
        model.setLookup(newLookup);
    }

    public void passwordChanged(String newPassword) {
        model.setPassword(newPassword);
    }

    public void loginButtonClicked() {
        AuthenticationResult result = authenticationService.authenticate(model.getLookup(), model.getPassword());
        if (!result.success) {
            view.showError(result.message);
        }
    }

}
