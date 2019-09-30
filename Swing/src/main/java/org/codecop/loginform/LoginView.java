package org.codecop.loginform;

public interface LoginView {

    void registerLoginListener(LoginListener listener);

    void showError(String message);

}
