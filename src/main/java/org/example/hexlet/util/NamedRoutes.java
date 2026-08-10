package org.example.hexlet.util;

public class NamedRoutes {

    public static String usersPath() {
        return "/users";
    }

    public static String buildUserPath() {
        return "/users/build";
    }
    public static String userPath(Long id) {
        return userPath(String.valueOf(id));
    }
    public static String userPath(String id) {
        return "/users/" + id;
    }

    public static String editUserPath(Long id) {
        return editUserPath(String.valueOf(id));
    }
    public static String editUserPath(String id) {
        return "/users/" + id + "/edit";
    }

    public static String updateUserPath(Long id) {
        return updateUserPath(String.valueOf(id));
    }
    public static String updateUserPath(String id) {
        return "/users/" + id;
    }


    public static String rootPath() {
        return "/";
    }






}
