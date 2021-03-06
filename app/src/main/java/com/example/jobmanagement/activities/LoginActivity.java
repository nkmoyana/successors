package com.example.jobmanagement.activities;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.view.ViewGroup;
import android.content.Intent;
import android.text.TextUtils;
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
    //String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.job_adverts);
        }

        jobProfileDao = Connections.getInstance(LoginActivity.this).getDatabase().getJobProfileDao();

        AppUtility.sharedpreferences = getSharedPreferences(getString(R.string.my_preference), MODE_PRIVATE);//PreferenceManager.getDefaultSharedPreferences(getBaseContext());

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
                    String email = "";
                    if(etEmail != null && !TextUtils.isEmpty(etEmail.getText()))
                    {
                        email = etEmail.getText().toString();
                    }
                    AppUtility.sharedpreferences.edit().putString(getString(R.string.email), email).apply();

                    String password = "";
                    if(etPassword != null && !TextUtils.isEmpty(etPassword.getText()))
                    {
                        password = etPassword.getText().toString();
                    }
                    AppUtility.sharedpreferences.edit().putString(getString(R.string.password), password).apply();
                }
            }
        });

        //userEmail = AppUtility.sharedpreferences.getString("email", "");
        //userPassword = AppUtility.sharedpreferences.getString("password", "");

        if(!(AppUtility.sharedpreferences.getString(getString(R.string.email), getString(R.string.empty_string)).isEmpty() && AppUtility.sharedpreferences.getString("password", "").isEmpty())){
            startActivity(new Intent(LoginActivity.this, ListAdvertActivity.class));
        }
    }

    public  void RegisterProfile(View view){
        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
    }

    public  void Login(View view){
        String email = getString(R.string.empty_string);
        if(etEmail != null && !TextUtils.isEmpty(etEmail.getText()))
        {
            email = etEmail.getText().toString();
        }

        String password = getString(R.string.empty_string);
        if(etPassword != null && !TextUtils.isEmpty(etPassword.getText()))
        {
            password = etPassword.getText().toString();
        }

        if(password.isEmpty() || email.isEmpty()) // AppUtility
        {
            View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

            AppUtility.ShowToast(LoginActivity.this, getString(R.string.enter_all_fields), toastView,2);        }
        else
        {
            String userEmail = getString(R.string.empty_string);
            if(etEmail != null && !TextUtils.isEmpty(etEmail.getText()))
            {
                userEmail = etEmail.getText().toString().trim();
            }

            String userPassword = getString(R.string.empty_string);
            if(etPassword != null && !TextUtils.isEmpty(etPassword.getText()))
            {
                userPassword = etPassword.getText().toString().trim();
            }

            new FindJobProfileAsync(jobProfileDao, new AsyncTaskCallback<JobProfile>() {
                @Override
                public void onSuccess(JobProfile success) {
                    String userEmail = getString(R.string.empty_string);
                    if(etEmail != null && !TextUtils.isEmpty(etEmail.getText()))
                    {
                        userEmail = etEmail.getText().toString().trim();
                    }

                    String userPassword = getString(R.string.empty_string);
                    if(etPassword != null && !TextUtils.isEmpty(etPassword.getText()))
                    {
                        userPassword = etPassword.getText().toString().trim();
                    }

                    Intent intent = new Intent(LoginActivity.this, ListAdvertActivity.class);
                    intent.putExtra(getString(R.string.user_email), userEmail);
                    intent.putExtra(getString(R.string.user_password), userPassword);
                    startActivity(intent);
                }

                @Override
                public void onException(Exception e) {
                    //errors on the TextFields here
                    //AppUtility.ShowToast(LoginActivity.this, e.getMessage());

                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                    AppUtility.ShowToast(LoginActivity.this, e.getMessage(), toastView,2);

                }
            }).execute(userEmail, userPassword); //two strings
        }
    }


}