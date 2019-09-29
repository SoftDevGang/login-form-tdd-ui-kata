package org.codecop.loginform;

public interface LoginView {

    void close();

    void showError(String message);

    void registerLoginListener(LoginListener listener);
}
