package com.ss.studysystem.cnf;

import com.ss.studysystem.Model.Users;
import com.ss.studysystem.UI.misc.Theme;
import javafx.scene.image.Image;

import java.util.prefs.Preferences;

public class user_cnf {

    private static user_cnf instance;

    private static final String PREFERENCE_NODE_NAME = "luminoom.user.cnf";

    private static final String USER_KEY = "user";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String EMAIL_KEY = "email";
    private static final String PROFILE_IMG_KEY = "profile_img";
    private static final String SALT_KEY = "salt";
    private static final String GENDER_KEY = "gender";
    private static final String THEME_KEY = "theme";

    private Users user;
    private int uid;
    private String username;
    private String password;
    private String email;
    private String salt;
    private String gender;
    private Image profile_img;
    private Theme theme = Theme.DEFAULT;

    public user_cnf() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Image getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(Image profile_img) {
        this.profile_img = profile_img;
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

    public void save(Users user){
        Preferences pref = Preferences.userRoot().node(PREFERENCE_NODE_NAME);
    }

    public void logout(){
        instance = null;
    }
}
