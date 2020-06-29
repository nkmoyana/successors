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
import com.example.jobmanagement.app_utilities.AppUtility;
import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.db_operations.Connections;
import com.example.jobmanagement.db_operations.JobProfileDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;
import com.example.jobmanagement.db_repositories.job_profile.FindJobProfileAsync;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout lytEmail, lytPassword;
    TextInputEditText etEmail, etPassword;
    Switch keepLogged;
    Button btnRegister, btnLogin;
    JobProfileDao jobProfileDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Job Adverts");

        jobProfileDao = Connections.getInstance(LoginActivity.this).getDatabase().getJobProfileDao();

        lytEmail = findViewById(R.id.lytEmail);
        lytPassword = findViewById(R.id.lytPassword);
        etEmail = findViewById(R.id.edtEmail);
        etPassword = findViewById(R.id.edtPassword);
        keepLogged = findViewById(R.id.swKeepLogged);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin =findViewById(R.id.btnLogin);

    }

    public  void RegisterProfile(View view){
        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
    }

    public  void Login(View view){
        if(etPassword.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty()) // AppUtility
        {
            Toast.makeText(LoginActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show(); //Do CustomToast Later
        }
        else
        {
            String userEmail, password;
            userEmail = etEmail.getText().toString().trim();
            password = etPassword.getText().toString().trim();

            new FindJobProfileAsync(jobProfileDao, new AsyncTaskCallback<JobProfile>() {
                @Override
                public void onSuccess(JobProfile success) {
                    startActivity(new Intent(LoginActivity.this, ListAdvertActivity.class));
                }

                @Override
                public void onException(Exception e) {
                    //errors on the TextFields here
                    AppUtility.ShowToast(LoginActivity.this, e.getMessage());
                }
            }).execute(userEmail, password); //two strings
        }
    }
}