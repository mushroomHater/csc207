package com.example.uoftlife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.data.GameConstants;

public class LoginActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    EditText name;
    EditText password;
    ImageView lookPassword;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = this.findViewById(R.id.name);
        password = this.findViewById(R.id.password);
        lookPassword = this.findViewById(R.id.lookPassword);
        login = this.findViewById(R.id.login);
        register = this.findViewById(R.id.register);

        lookPassword.setOnTouchListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            Editable etable = password.getText();
            Selection.setSelection(etable, etable.length());
        } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            Editable etable = password.getText();
            Selection.setSelection(etable, etable.length());
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            lookPassword.setImageResource(R.drawable.look);
            password.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            Editable etable = password.getText();
            Selection.setSelection(etable, etable.length());
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == login) {
            final String name = this.name.getText().toString();
            final String pwd = password.getText().toString();
            if (name.isEmpty()) {
                showToast(getString(R.string.username_empty));
                return;
            }
            if (!UserManager.checkUserExist(name)) {
                showToast(getString(R.string.username_not_exist_error));
                return;
            }
            if (pwd.isEmpty()) {
                showToast(getString(R.string.password_empty));
                return;
            }
            User user = UserManager.authenticate(name, pwd);
            if (user == null) {
                showToast(getString(R.string.password_not_right));
            } else {
                showToast(getString(R.string.login_success));

                SharedPreferences myPreference=getSharedPreferences(GameConstants.USER_FILE, Context.MODE_PRIVATE);
                Editor editor = myPreference.edit();
                editor.putBoolean("login", true);
                editor.putString("name", name);
                editor.apply();

                finish();
            }
        } else if (view == register) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
