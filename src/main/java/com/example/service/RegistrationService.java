package com.example.service;

import com.example.model.User;
import com.example.util.EmailValidator;

import java.util.Map;
import java.util.HashMap;

public class RegistrationService {
    private Map<String, User> registeredUsers;

    public RegistrationService() {
        this.registeredUsers = new HashMap<String, User>();
    }

    public boolean registerUser(String email) {
        if (!EmailValidator.isValidEmail(email)) {
            return false;
        }

        if (registeredUsers.containsKey(email)) {
            return false;
        }

        User newUser = new User(email);
        registeredUsers.put(email, newUser);
        return true;
    }

    public User getUser(String email) {
        return registeredUsers.get(email);
    }
}
