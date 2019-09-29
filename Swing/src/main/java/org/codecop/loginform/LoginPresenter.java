package org.codecop.loginform;

public class LoginPresenter {

    private final LoginModel model;

    public LoginPresenter(LoginModel model, LoginView view) {
        this.model = model;
    }

    public void lookupChanged(String newLookup) {
        if (newLookup.isEmpty()) {
            model.setLoginButtonActive(false);
        }

        model.setLookup(newLookup);

        enableButtonIfFieldsSet();
    }

    private void enableButtonIfFieldsSet() {
        if (model.hasLookup() && model.hasPassword()) {
            model.setLoginButtonActive(true);
        }
    }

    public void passwordChanged(String newPassword) {
        if (newPassword.isEmpty()) {
            model.setLoginButtonActive(false);
        }

        model.setPassword(newPassword);

        enableButtonIfFieldsSet();
    }

}
