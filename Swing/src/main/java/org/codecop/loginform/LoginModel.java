package org.codecop.loginform;

import java.util.function.BooleanSupplier;

public class LoginModel {

    private String lookup;
    private String password;

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
        return false;
    }

    public void setLoginButtonActive(boolean state) {

    }

}
