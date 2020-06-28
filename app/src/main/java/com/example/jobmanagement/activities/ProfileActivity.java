package com.example.jobmanagement.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.jobmanagement.R;
import com.example.jobmanagement.app_utilities.AppUtility;
import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;
import com.example.jobmanagement.db_repositories.job_profile.InsertJobProfileAsync;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileActivity extends AppCompatActivity {

    TextInputLayout lytEmail, lytPassword, lytConfirm, lytName, lytLastName, lytPhone, lytID;
    TextInputEditText edtEmail, edtPassword, edtConfirm, edtName, edtLastName, edtPhone, edtID;

    TextInputLayout lytQualificationDropdown, lytFieldDropdown;
    AutoCompleteTextView dropdownText, dropdownTextField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register Profile");

        lytEmail = findViewById(R.id.lytEmail);
        lytPassword = findViewById(R.id.lytPassword);
        lytConfirm = findViewById(R.id.lytConfirmPassword);
        lytName = findViewById(R.id.lytFirstName);
        lytLastName = findViewById(R.id.lytLastName);
        lytPhone = findViewById(R.id.lytCellNr);
        lytID = findViewById(R.id.lytId);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirm = findViewById(R.id.edtConfirmPassword);
        edtName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtPhone = findViewById(R.id.edtCellNr);
        edtID = findViewById(R.id.edtId);

        lytQualificationDropdown = findViewById(R.id.lytQualification_dropdown);
        dropdownText = findViewById(R.id.dropdown_text);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.qualification_list, R.layout.menu_dropdown);
        adapter1.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownText.setAdapter(adapter1);

        lytFieldDropdown = findViewById(R.id.lytField_dropdown);
        dropdownText = findViewById(R.id.dropdown_text_field);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.education_list, R.layout.menu_dropdown);
        adapter.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownText.setAdapter(adapter);

    }

    public void OnClick(View view) {
        switch(view.getId())
        {
            case R.id.btnRegister:
                JobProfile jobProfile = new JobProfile();
                jobProfile.setId(123456789101112L);
                jobProfile.setEmail("moses.outlook@outlook.com");
                jobProfile.setName("Moses");
                jobProfile.setSurname("Tsotetsi");
                jobProfile.setCellphone("076 543 2105");
                jobProfile.setIdentityNumber("9005115698080");
                jobProfile.setQualification("Masters");
                jobProfile.setEducation("Information Technology");
//
                new InsertJobProfileAsync(jobProfile, new AsyncTaskCallback<JobProfile>() {
                    @Override
                    public void onSuccess(JobProfile success) {
                        AppUtility.ShowToast(ProfileActivity.this,success.getName() + "\n" + success.getSurname()
                                + " Added!");
                    }

                    @Override
                    public void onException(Exception e) {
                        AppUtility.ShowToast(ProfileActivity.this, e.getMessage());
                    }
                }).execute();
                break;
        }
    }
}