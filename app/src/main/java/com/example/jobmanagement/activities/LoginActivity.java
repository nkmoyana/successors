package com.example.jobmanagement.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
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
    String userEmail, userPassword;
    //SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Job Adverts");

        jobProfileDao = Connections.getInstance(LoginActivity.this).getDatabase().getJobProfileDao();

        AppUtility.sharedpreferences = getSharedPreferences("my Preference", MODE_PRIVATE);//PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        lytEmail = findViewById(R.id.lytEmail);
        lytPassword = findViewById(R.id.lytPassword);
        etEmail = findViewById(R.id.edtEmail);
        etPassword = findViewById(R.id.edtPassword);
        keepLogged = findViewById(R.id.swKeepLogged);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin =findViewById(R.id.btnLogin);

        keepLogged.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    AppUtility.sharedpreferences.edit().putString("email", etEmail.getText().toString()).apply();
                    AppUtility.sharedpreferences.edit().putString("password", etPassword.getText().toString()).apply();
                }
            }
        });

        userEmail = AppUtility.sharedpreferences.getString("email", "");
        userPassword = AppUtility.sharedpreferences.getString("password", "");

         if(!(userEmail.isEmpty() && userPassword.isEmpty())){
             startActivity(new Intent(LoginActivity.this, ListAdvertActivity.class));
         }
    }

    public  void RegisterProfile(View view){
        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
    }

    public  void Login(View view){
        if(etPassword.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty()) // AppUtility
        {
            View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

            AppUtility.ShowToast(LoginActivity.this, "Please enter all fields", toastView,2);        }
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
                    //AppUtility.ShowToast(LoginActivity.this, e.getMessage());

                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                    AppUtility.ShowToast(LoginActivity.this, e.getMessage(), toastView,2);

                }
            }).execute(userEmail, password); //two strings
        }
    }


}