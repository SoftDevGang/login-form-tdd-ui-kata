package org.codecop.loginform;

public class LoginPresenter {

    private final LoginModel model;
    private final LoginView view;

    public LoginPresenter(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;
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

}
