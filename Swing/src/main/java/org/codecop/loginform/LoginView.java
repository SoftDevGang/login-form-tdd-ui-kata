package org.codecop.loginform;

public interface LoginView {

    void enableLogin();

    void disableLogin();

    void close();

    void showError(String message);

    void registerLoginListener(LoginListener listener);
}
