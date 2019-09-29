package org.codecop.loginform;

public class LoginModel {

    private String lookup;
    private String password;
    private boolean loginButtonActive;

    public void setLookup(String lookup) {
        this.lookup = lookup;
    }

    public String getLookup() {
        return lookup;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean getLoginButtonActive() {
        return loginButtonActive;
    }

    public void setLoginButtonActive(boolean state) {
        loginButtonActive = state;
    }

}
