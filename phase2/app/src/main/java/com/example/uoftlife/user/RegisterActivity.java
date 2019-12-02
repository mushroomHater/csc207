package com.example.uoftlife.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText register_name;
    EditText register_password;
    EditText register_password_again;
    Button register_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_name = this.findViewById(R.id.register_name);
        register_password = this.findViewById(R.id.register_password);
        register_password_again = this.findViewById(R.id.register_password_again);
        register_confirm = this.findViewById(R.id.register_confirm);

        register_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String name = register_name.getText().toString();
        final String pwd = register_password.getText().toString();
        final String pwd_again = register_password_again.getText().toString();
        if (name.isEmpty()) {
            showToast(getString(R.string.username_empty));
            return;
        }
        if (UserManager.checkUserExist(name)) {
            showToast(getString(R.string.username_exist_error));
            return;
        }
        if (pwd.isEmpty()) {
            showToast(getString(R.string.password_empty));
            return;
        }
        if (pwd_again.isEmpty()) {
            showToast(getString(R.string.password_again_empty));
            return;
        }
        if (!pwd.equals(pwd_again)){
            showToast(getString(R.string.two_password_not_equal));
            return;
        }
        User user = UserManager.signUp(name, pwd);
        if (user == null) {
            showToast(getString(R.string.unknown_error));
        } else {
            showToast(getString(R.string.sign_up_success));
            UserManager.saveToFile(RegisterActivity.this);
            finish();
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
