package com.example.service;

import com.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationServiceTest {
    private RegistrationService registrationService;

    @BeforeEach
    void setup(){
        registrationService = new RegistrationService();
    }

    @Test
    void testRegisterNewUser(){
        boolean result = registrationService.registerUser("john@example.com");
        assertTrue(result, "Registration should succeed for a new user.");
    }

    @Test
    void testRegisterDuplicateUser() {
        registrationService.registerUser("jane@example.com");
        boolean result = registrationService.registerUser("jane@example.com");
        assertFalse(result, "Registration should fail for a duplicate user.");
    }

    @Test
    void testRegisterEmptyEmail() {
        boolean result = registrationService.registerUser("");
        assertFalse(result, "Registration should fail for an empty email.");
    }

    @Test
    void testRegisterInvalidEmail() {
        boolean result = registrationService.registerUser("invalid@email");
        assertFalse(result, "Registration should fail for an invalid email format.");
    }

    @Test
    void testRegisterNullEmail() {
        boolean result = registrationService.registerUser(null);
        assertFalse(result, "Registration should fail for a null email.");
    }

    @Test
    void testGetRegisteredUser() {
        String email = "test@example.com";
        registrationService.registerUser(email);
        User user = registrationService.getUser(email);
        assertNotNull(user, "User should be registered and retrieved successfully.");
        assertEquals(email, user.getEmail(), "Retrieved user email should match the registered email.");
    }

    @Test
    void testGetUnregisteredUser() {
        User user = registrationService.getUser("unregistered@example.com");
        assertNull(user, "User should be null for an unregistered email.");
    }
}
