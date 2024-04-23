package com.example;

import com.example.model.User;
import com.example.service.RegistrationService;

import java.util.Scanner;

public class MovieListingApp {
    private static final RegistrationService registrationService = new RegistrationService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while(!exit){
            System.out.println("Welcome to the Movie Listing App!");
            System.out.println("1. Register");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    exit = true;
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        boolean isRegistered = registrationService.registerUser(email);
        if (isRegistered) {
            System.out.println("Registration successful!");
            User user = registrationService.getUser(email);
            handleUserMenu(user);
        } else {
            System.out.println("Registration failed. Please try again with a valid email address.");
        }
    }

    private static void handleUserMenu(User user) {

    }
}
