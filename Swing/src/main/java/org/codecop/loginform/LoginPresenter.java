package org.codecop.loginform;

public class LoginPresenter {

    private final LoginModel model;

    public LoginPresenter(LoginModel model) {
        this.model = model;
    }

    public void lookupChanged(String newLookup) {
        if (newLookup.isEmpty()) {
            model.setLoginButtonActive(false);
        }

        if (model.getLookup() != null && !model.getLookup().isEmpty() && model.getPassword() != null && !model.getPassword().isEmpty()) {
            model.setLoginButtonActive(true);
        }
    }

    public void passwordChanged(String newPassword) {
        if (newPassword.isEmpty()) {
            model.setLoginButtonActive(false);
        }

        if (model.getLookup() != null && !model.getLookup().isEmpty() && model.getPassword() != null && !model.getPassword().isEmpty()) {
            model.setLoginButtonActive(true);
        }
    }

}
