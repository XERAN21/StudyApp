package com.ss.studysystem.cnf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.UI.misc.Theme;
import javafx.scene.image.Image;

import java.util.prefs.Preferences;

public class user_cnf {

    private static user_cnf instance;

    private static final String PREFERENCE_NODE_NAME = "luminoom.user.cnf";

    private final Gson gson = new GsonBuilder().create();

    private static final String USER_KEY = "user";
    private static final String THEME_KEY = "theme";

    private Users user;
    private Theme theme = Theme.DEFAULT;

    public user_cnf() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public static user_cnf get_instance(){
        if(instance == null){
            instance = new user_cnf();
        }
        return instance;
    }

    public void save(Users user) {
        Preferences pref = Preferences.userRoot().node(PREFERENCE_NODE_NAME);
        String userJson = gson.toJson(user);
        pref.put(USER_KEY, userJson);
        this.user = user;
    }

    public void save_theme(Theme theme){
        Preferences pref = Preferences.userRoot().node(PREFERENCE_NODE_NAME);
        pref.put(THEME_KEY, String.valueOf(theme));
        this.theme = theme;
    }

    public void load() {
        Preferences pref = Preferences.userRoot().node(PREFERENCE_NODE_NAME);
        String userJson = pref.get(USER_KEY, null);
        String user_theme_str = pref.get(THEME_KEY, "LIGHT");
        Theme user_theme = Theme.valueOf(user_theme_str);
        if (userJson != null) {
            this.user = gson.fromJson(userJson, Users.class);
            this.theme = user_theme;
        }
    }

    public void logout() {
        Preferences pref = Preferences.userRoot().node(PREFERENCE_NODE_NAME);
        pref.remove(USER_KEY);
        instance = null;
    }

    public static void main(String[] args) {
        user_cnf config = user_cnf.get_instance();

//        Users user = new Users();
//        user.setId(1);
//        user.setUsername("JohnDoe");
//        user.setEmail("johndoe@example.com");
//        config.save(user);
//
//        config.load();
//        Users loadedUser = config.getUser();
//        if (loadedUser != null) {
//            System.out.println("Loaded User: " + loadedUser.getUsername() + " - " + loadedUser.getEmail());
//        } else {
//            System.out.println("No user found.");
//        }

        config.logout();
    }
}
