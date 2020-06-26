package com.example.jobmanagement.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.jobmanagement.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout lytEmail, lytPassword;
    TextInputEditText etEmail, etPassword;
    Switch keepLogged;
    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Job Adverts");

        lytEmail = findViewById(R.id.lytEmail);
        lytPassword = findViewById(R.id.lytPassword);
        etEmail = findViewById(R.id.edtEmail);
        etPassword = findViewById(R.id.edtPassword);
        keepLogged = findViewById(R.id.swKeepLogged);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin =findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterProfile.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPassword.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show(); //Do CustomToast Later
                }
                else
                {

                }
            }
        });

    }
}