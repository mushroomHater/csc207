package com.example.uoftlife;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.InputMismatchException;

public class UserManager {
    private HashMap<String, User> users;
    private Context context;
    private static final String FILENAME = "users.dat";


    public UserManager(Context context) {
        this.context = context;
        loadUsers();
    }

    private void loadUsers(){
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

    private void SaveToFile(){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(FILENAME, context.MODE_PRIVATE));
            outputStream.writeObject(users);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public User getUser(String username, String password){
        User result = users.get(username);
        if(result.getPassword() != null && result.getPassword().equals(password)){
            return result;
        }
        return null;
    }

    User authenticate(String username, String password){
        if(!users.containsKey(username)) {
            throw new InputMismatchException();
        }

        if(!users.get(username).checkPassword(password)) {
            throw new InputMismatchException();
        }

        return users.get(username);
    }

}
