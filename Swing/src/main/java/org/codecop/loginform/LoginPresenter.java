package org.codecop.loginform;

public class LoginPresenter {

    private final LoginModel model;

    public LoginPresenter(LoginModel model, LoginView view) {
        this.model = model;
    }

    public void lookupChanged(String newLookup) {
        model.setLookup(newLookup);
    }

    public void passwordChanged(String newPassword) {
        model.setPassword(newPassword);
    }

}
