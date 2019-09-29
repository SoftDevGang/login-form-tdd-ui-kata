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
    }

    public void lookupChanged(String newLookup) {
        if (newLookup.isEmpty()) {
            disableLoginButton();
        }

        model.setLookup(newLookup);

        enableLoginButtonIfFieldsSet();
    }

    private void disableLoginButton() {
        model.setLoginButtonActive(false);
        view.disableLogin();
    }

    private void enableLoginButtonIfFieldsSet() {
        if (model.hasLookup() && model.hasPassword()) {
            model.setLoginButtonActive(true);
            view.enableLogin();
        }
    }

    public void passwordChanged(String newPassword) {
        if (newPassword.isEmpty()) {
            disableLoginButton();
        }

        model.setPassword(newPassword);

        enableLoginButtonIfFieldsSet();
    }

    public void loginButtonClicked() {
        AuthenticationResult result = authenticationService.authenticate(model.getLookup(), model.getPassword());
        if (result.success) {
            view.close();
        } else {
            view.showError(result.message);
        }
    }

}
