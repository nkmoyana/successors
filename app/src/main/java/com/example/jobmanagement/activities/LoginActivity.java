package com.example.jobmanagement.activities;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.view.ViewGroup;
import android.content.Intent;
import com.example.jobmanagement.R;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.app_utilities.AppUtility;
import com.example.jobmanagement.db_operations.Connections;
import com.example.jobmanagement.db_operations.JobProfileDao;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;
import com.example.jobmanagement.db_repositories.job_profile.FindJobProfileAsync;



public class LoginActivity extends AppCompatActivity {

    TextInputLayout lytEmail, lytPassword;
    TextInputEditText etEmail, etPassword;
    Switch keepLogged;
    Button btnRegister, btnLogin;
    JobProfileDao jobProfileDao;
    String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.job_adverts);
        }

        jobProfileDao = Connections.getInstance(LoginActivity.this).getDatabase().getJobProfileDao();

        AppUtility.sharedpreferences = getSharedPreferences("my Preference", MODE_PRIVATE);

        lytEmail = findViewById(R.id.lytEmail);
        lytPassword = findViewById(R.id.lytPassword);
        etEmail = findViewById(R.id.edtEmail);
        etPassword = findViewById(R.id.edtPassword);
        keepLogged = findViewById(R.id.swKeepLogged);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin =findViewById(R.id.btnLogin);

        AppUtility.setOnFocusChangeListener(etEmail,getString(R.string.email));
        AppUtility.setOnFocusChangeListener(etPassword,getString(R.string.password));

        keepLogged.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppUtility.sharedpreferences.edit().putString("email", etEmail.getText().toString()).apply();
                    AppUtility.sharedpreferences.edit().putString("password", etPassword.getText().toString()).apply();
                }
            }
        });

        //userEmail = AppUtility.sharedpreferences.getString("email", "");
        //userPassword = AppUtility.sharedpreferences.getString("password", "");

        if(!(AppUtility.sharedpreferences.getString("email", "").isEmpty() && AppUtility.sharedpreferences.getString("password", "").isEmpty())){
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

            AppUtility.ShowToast(LoginActivity.this, getString(R.string.enter_all_fields), toastView,2);        }
        else
        {
            String userEmail, password;
            userEmail = etEmail.getText().toString().trim();
            password = etPassword.getText().toString().trim();

            new FindJobProfileAsync(jobProfileDao, new AsyncTaskCallback<JobProfile>() {
                @Override
                public void onSuccess(JobProfile success) {
                    Intent intent = new Intent(LoginActivity.this, ListAdvertActivity.class);
                    intent.putExtra("userEmail", etEmail.getText().toString().trim());
                    intent.putExtra("userPassword", etPassword.getText().toString().trim());
                    startActivity(intent);
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