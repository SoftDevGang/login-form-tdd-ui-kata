package org.codecop.loginform;

public class LoginPresenter {

    private final LoginModel model;
    private final LoginView view;

    public LoginPresenter(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;
    }

    public void lookupChanged(String newLookup) {
        model.setLookup(newLookup);
    }

    public void passwordChanged(String newPassword) {
        model.setPassword(newPassword);
    }

    public void loginButtonClicked() {
        view.close();
    }

}
