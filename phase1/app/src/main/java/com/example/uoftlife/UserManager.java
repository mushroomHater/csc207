package com.example.uoftlife;


import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.InputMismatchException;

public class UserManager {
    private static HashMap<String, User> users;
    private static final String FILENAME = "users.dat";
    private static User currentUser;
    private UserManager() {

    }



    public static void loadUsers(Context context){
        try {
            InputStream inputStream = context.openFileInput(FILENAME);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                users = (HashMap<String, User>) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            users = new HashMap<>();
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
            users = new HashMap<>();
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contains unexpected data type: " + e.toString());
            users = new HashMap<>();
        }
    }

    public static void saveToFile(Context context){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(FILENAME, Context.MODE_PRIVATE));
            outputStream.writeObject(users);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static boolean checkUserExist(String username) {
        return users.containsKey(username);
    }

    //login
    public static User authenticate(String username, String password){
        if (!checkUserExist(username)) {
            return null;
        }
        if(!users.get(username).checkPassword(password)) {
            return null;
        }
        currentUser = users.get(username);
        return currentUser;
    }

    // sign in
    public static User signIn(String username, String password) {
        if (checkUserExist(username)) {
            return null;
        }
        currentUser= new User(username, password, username.hashCode());
        users.put(currentUser.getUsername(), currentUser);
        return currentUser;
    }


}
