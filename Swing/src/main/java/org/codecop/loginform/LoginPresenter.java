package org.codecop.loginform;

import org.codecop.auth.AuthenticationResult;
import org.codecop.auth.AuthenticationService;

public class LoginPresenter implements LoginListener {

    private final LoginModel model;
    private final LoginView view;
    private final AuthenticationService authenticationService;

    public LoginPresenter(LoginModel model, LoginView view, AuthenticationService authenticationService) {
        this.model = model;
        this.view = view;
        this.authenticationService = authenticationService;

        view.registerLoginListener(this);
    }

    @Override
    public void lookupChanged(String newLookup) {
        model.setLookup(newLookup);
    }

    @Override
    public void passwordChanged(String newPassword) {
        model.setPassword(newPassword);
    }

    @Override
    public void loginButtonClicked() {
        AuthenticationResult result = authenticationService.authenticate(model.getLookup(), model.getPassword());
        if (!result.success) {
            view.showError(result.message);
        }
    }

}
