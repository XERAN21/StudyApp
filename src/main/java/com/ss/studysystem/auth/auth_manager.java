package com.ss.studysystem.auth;

import com.password4j.Password;
import com.password4j.ScryptFunction;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.controller.user_controller;

import java.security.SecureRandom;
import java.util.Base64;

public class auth_manager {

    private static final ScryptFunction scrypt = ScryptFunction
            .getInstance(64, 24, 8, 32);

    public static boolean add_new_user(Users user) {
        String salt = generate_salt();
        String hashed = hash_password(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(hashed);
        return user_controller.create_user(user);
    }

    private static String generate_salt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);

    }

    public static String hash_password(String password, String salt) {
        return Password.hash(password)
                .addSalt(salt)
                .with(scrypt).getResult();
    }

    public static boolean verify_password(String plainPassword, String hashedPassword, String salt) {
        return Password.check(plainPassword, hashedPassword)
                .addSalt(salt)
                .with(scrypt);
    }

    public static void main(String[] args) {
        String password = "MySecurePassword";

        String salt = generate_salt();
        System.out.println("Generated Salt: " + salt);

        String hashedPassword = hash_password(password, salt);
        System.out.println("Hashed Password: " + hashedPassword);

        boolean isMatch = verify_password(password, hashedPassword, salt);
        System.out.println("Password Match: " + isMatch);

    }

}
