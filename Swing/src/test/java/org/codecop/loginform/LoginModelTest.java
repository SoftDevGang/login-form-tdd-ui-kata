package org.codecop.loginform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LoginModelTest {

    LoginModel model = new LoginModel();

    @Test
    void shouldContainUserLookupAndPassword() {
        model.setLookup("user@server.com");
        model.setPassword("secret123");

        assertEquals("user@server.com", model.getLookup());
        assertEquals("secret123", model.getPassword());
    }

    @Test
    void shouldControlLoginButton() {
        assertFalse(model.getLoginButtonActive());
        model.setLoginButtonActive(true);
        assertTrue(model.getLoginButtonActive());
    }
}
