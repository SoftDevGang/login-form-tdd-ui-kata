package org.codecop.loginform;

public class LoginPresenter {

    private final LoginModel model;

    public LoginPresenter(LoginModel model) {
        this.model = model;
    }

    public void lookupChanged(String lookup) {
        if (lookup.isEmpty()) {
            model.setLoginButtonActive(false);
        }
    }

}
