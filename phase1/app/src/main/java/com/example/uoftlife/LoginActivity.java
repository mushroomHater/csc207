package com.example.uoftlife;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private boolean login = true;
    private EditText username;
    private EditText password;
    private TextView title;
    private Button left;
    private Button right;
    private static final String USER_SHARED = "user_shared";
    private static final String USER_NAME = "user_name";
    private static final String USER_PASSWORD = "user_password";
    private SharedPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        title = findViewById(R.id.tv_title);
        left = findViewById(R.id.bt_left);
        right = findViewById(R.id.bt_right);
        userPreferences = getSharedPreferences(USER_SHARED, MODE_PRIVATE);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = false;
                title.setText(getString(R.string.sign_in));
                right.setText(getString(R.string.sign_in));
                right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String name = username.getText().toString();
                        final String pwd = password.getText().toString();
                        if (name.isEmpty()) {
                            username.requestFocus();
                            showToast(getString(R.string.username_empty));
                            return;
                        }
                        if (UserManager.checkUserExist(name)) {
                            username.requestFocus();
                            showToast(getString(R.string.username_exist_error));
                            return;
                        }
                        if (pwd.isEmpty()) {
                            password.requestFocus();
                            showToast(getString(R.string.password_empty));
                            return;
                        }
                        User user = UserManager.signIn(name, pwd);
                        if (user == null) {
                            showToast(getString(R.string.unknown_error));
                        } else {
                            userPreferences.edit().putString(USER_NAME, name)
                                    .putString(USER_PASSWORD, pwd)
                                    .apply();
                            showToast(getString(R.string.sign_in_success));
                            finish();
                        }
                    }
                });
                left.setVisibility(View.INVISIBLE);
            }
        });
        setLoginButtonClick();

    }

    private void setLoginButtonClick() {
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = username.getText().toString();
                final String pwd = password.getText().toString();
                if (name.isEmpty()) {
                    username.requestFocus();
                    showToast(getString(R.string.username_empty));
                    return;
                }
                if (!UserManager.checkUserExist(name)) {
                    showToast(getString(R.string.username_not_exist_error));
                    username.requestFocus();
                    return;
                }
                if (pwd.isEmpty()) {
                    showToast(getString(R.string.password_empty));
                    password.requestFocus();
                    return;
                }
                User user = UserManager.authenticate(name, pwd);
                if (user == null) {
                    showToast(getString(R.string.password_not_right));
                    password.requestFocus();
                } else {
                    userPreferences.edit().putString(USER_NAME, name)
                            .putString(USER_PASSWORD, pwd)
                            .apply();
                    showToast(getString(R.string.login_success));
                    finish();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (!login) {
            title.setText(getString(R.string.login));
            right.setText(getString(R.string.login));
            setLoginButtonClick();
            login = true;
            left.setVisibility(View.VISIBLE);
        } else {
            finishAffinity();
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
