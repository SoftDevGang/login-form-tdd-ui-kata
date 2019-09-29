package org.codecop.auth;

public class AuthenticationResult {

    public final boolean success;
    public final String message;

    public AuthenticationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
