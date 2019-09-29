package org.codecop.loginform;

public interface LoginListener {

    void lookupChanged(String lookup);

    void passwordChanged(String password);

    void loginButtonClicked();

}
