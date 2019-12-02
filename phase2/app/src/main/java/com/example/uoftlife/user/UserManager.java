package com.example.uoftlife.user;


import android.content.Context;
import android.util.Log;

import com.example.uoftlife.data.GameConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A user manager class.
 */
public class UserManager {
    private static Map<String, User> users;
    private static final String FILENAME = "users.dat";
    private static User currentUser;
    private UserManager() {

    }

    public static Map<String, User> getUsers() {
        return users;
    }

    public static void setCurrentUser(User currentUser) {
        UserManager.currentUser = currentUser;
    }

    /**
     * Load users from file.
     */
    public static void loadUsers(Context context){
        try {
            InputStream inputStream = context.openFileInput(FILENAME);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                users = (HashMap<String, User>) input.readObject();
                inputStream.close();
            }
        } catch (Exception e) {
            users = new HashMap<>();
        }
    }

    /**
     * Save users to file.
     */
    static void saveToFile(Context context){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(FILENAME, Context.MODE_PRIVATE));

            outputStream.writeObject(users);
            outputStream.close();
            for (Map.Entry<String,User> entry : users.entrySet())
                Log.e(GameConstants.USER_FILE, "save " + entry.getKey());

        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Check if the user exists.
     */
    static boolean checkUserExist(String username) {
        return users.containsKey(username);
    }

    /**
     * Log in.
     */
    static User authenticate(String username, String password){
        if (!checkUserExist(username)) {
            return null;
        }
        if(!users.get(username).checkPassword(password)) {
            return null;
        }
        currentUser = users.get(username);
        return currentUser;
    }

    /**
     * Sign up.
     */
    static User signUp(String username, String password) {
        if (checkUserExist(username)) {
            return null;
        }
        currentUser= new User(username, password, username.hashCode());
        users.put(currentUser.getUsername(), currentUser);
        return currentUser;
    }

    /**
     * Return current user.
     */
    public static User getCurrentUser(){
        return currentUser;
    }


}
