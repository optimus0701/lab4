package com.optimus.se.lab.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputLayout;
import com.optimus.se.lab.R;
import com.optimus.se.lab.user.Manager;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout layoutUserName;
    private TextInputLayout layoutPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btnLogin.setOnClickListener(view -> {
            EditText edtUsername = layoutUserName.getEditText();
            EditText edtPassword = layoutPassword.getEditText();
            if (edtUsername != null && edtPassword != null) {
                if (!isValidUsernameAndPassword(edtUsername, edtPassword)) {
                    return;
                }

                loginWithUsernameAndPassword(edtUsername, edtPassword);

            } else {
                Toast.makeText(this, "layout username or password do not have edittext", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void loginWithUsernameAndPassword(EditText edtUsername, EditText edtPassword) {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        Intent intent = new Intent(this, TimeTableActivity.class);
        if (username.equals("manager") && password.equals("123456")) {
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            intent.putExtra("type", 2);
        } else if (username.equals("teacher") && password.equals("111111")) {
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            intent.putExtra("type", 1);
        } else if (username.equals("student") && password.equals("123456")) {
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            intent.putExtra("type", 0);
        } else {
            Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
        finish();
    }

    private void initView() {
        layoutUserName = findViewById(R.id.edt_login_username);
        layoutPassword = findViewById(R.id.edt_login_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    private boolean isValidUsernameAndPassword(EditText edtUsername, EditText edtPassword) {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        boolean results = true;

        if (username.isEmpty()) {
            layoutUserName.setError("This field can not be empty!");
            results = false;
        }

        if (password.isEmpty()) {
            layoutPassword.setError("This field can not be empty!");
            results = false;
        }

        if (!username.isEmpty() && username.length() < 6) {
            layoutUserName.setError("Username must be greater than 6 characters");
            results = false;
        }

        return results;
    }

}
