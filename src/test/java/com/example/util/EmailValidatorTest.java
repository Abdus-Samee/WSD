package com.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {
    @Test
    public void testValidEmail() {
        // Arrange
        String validEmail = "test@example.com";

        // Act
        boolean isValid = EmailValidator.isValidEmail(validEmail);

        // Assert
        assertTrue(isValid);
    }

    @Test
    public void testInvalidEmail_Null() {
        // Arrange
        String invalidEmail = null;

        // Act
        boolean isValid = EmailValidator.isValidEmail(invalidEmail);

        // Assert
        assertFalse(isValid);
    }

    @Test
    public void testInvalidEmail_Empty() {
        // Arrange
        String invalidEmail = "";

        // Act
        boolean isValid = EmailValidator.isValidEmail(invalidEmail);

        // Assert
        assertFalse(isValid);
    }

    @Test
    public void testInvalidEmail_InvalidFormat() {
        // Arrange
        String invalidEmail = "invalid-email";

        // Act
        boolean isValid = EmailValidator.isValidEmail(invalidEmail);

        // Assert
        assertFalse(isValid);
    }

    @Test
    public void testInvalidEmail_MissingDomain() {
        // Arrange
        String invalidEmail = "test@example";

        // Act
        boolean isValid = EmailValidator.isValidEmail(invalidEmail);

        // Assert
        assertFalse(isValid);
    }

    @Test
    public void testInvalidEmail_MissingUsername() {
        // Arrange
        String invalidEmail = "@example.com";

        // Act
        boolean isValid = EmailValidator.isValidEmail(invalidEmail);

        // Assert
        assertFalse(isValid);
    }
}
