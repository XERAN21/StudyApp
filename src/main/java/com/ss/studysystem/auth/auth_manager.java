package com.ss.studysystem.auth;

import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.ScryptFunction;
import com.ss.studysystem.Model.Users;

import java.util.Random;
import java.util.UUID;

public class auth_manager {

    private static final ScryptFunction scrypt = ScryptFunction
            .getInstance(64, 24, 8, 32);


    public static boolean add_new_user(Users user){

        return true;
    }

    private static String generate_salt() {
      //todo
        return null;
    }


}
