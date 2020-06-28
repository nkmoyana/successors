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
import com.example.jobmanagement.db_operations.AppDatabase;
import com.example.jobmanagement.db_operations.Connections;
import com.example.jobmanagement.db_operations.JobAdvertDao;
import com.example.jobmanagement.db_operations.JobProfileDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;
import com.example.jobmanagement.db_repositories.job_profile.InsertJobProfileAsync;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileActivity extends AppCompatActivity {

    TextInputLayout lytEmail, lytPassword, lytConfirm, lytName, lytLastName, lytPhone, lytID;
    TextInputEditText edtEmail, edtPassword, edtConfirm, edtName, edtLastName, edtPhone, edtID;

    TextInputLayout lytQualificationDropdown, lytFieldDropdown;
    AutoCompleteTextView dropdownText, dropdownTextField;

    JobProfileDao jobProfileDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register Profile");

        jobProfileDao = Connections.getInstance(ProfileActivity.this).getDatabase().getJobProfileDao();

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
        dropdownTextField = findViewById(R.id.dropdown_text_field);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.qualification_list, R.layout.menu_dropdown);
        adapter1.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownText.setAdapter(adapter1);

        lytFieldDropdown = findViewById(R.id.lytField_dropdown);
        dropdownText = findViewById(R.id.dropdown_text_field);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.education_list, R.layout.menu_dropdown);
        adapter.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownText.setAdapter(adapter);

    }

    public void Register(View view){

        JobProfile jobProfile = new JobProfile();

        boolean TextInputEditTextHasText = AppUtility.TextInputEditTextHasText(edtEmail, edtPassword, edtConfirm, edtName, edtLastName, edtPhone, edtID);
        boolean AutoCompleteTextViewHasText = AppUtility.AutoCompleteTextViewHasText(dropdownText, dropdownTextField);
        if(TextInputEditTextHasText && AutoCompleteTextViewHasText){
            jobProfile.setEmail(edtEmail.getText().toString().trim());
            jobProfile.setPassword(edtPassword.getText().toString().trim());
            jobProfile.setName(edtName.getText().toString().trim());
            jobProfile.setSurname(edtLastName.getText().toString().trim());
            jobProfile.setCellphone(edtPhone.getText().toString().trim());
            jobProfile.setIdentityNumber(edtID.getText().toString().trim());
            jobProfile.setEducation(dropdownText.getText().toString());
            jobProfile.setQualification(dropdownTextField.getText().toString());

            new InsertJobProfileAsync(jobProfileDao, new AsyncTaskCallback<JobProfile>() {
                @Override
                public void onSuccess(JobProfile success) {
                    AppUtility.ShowToast(ProfileActivity.this, "Hi "+ success.getName() + " " + success.getSurname() + ", Your profile has been created");
                    ProfileActivity.this.finish();
                }

                @Override
                public void onException(Exception e) {
                    AppUtility.ShowToast(ProfileActivity.this, e.getMessage());
                }
            }).execute(jobProfile);
        }
        else {
            AppUtility.ShowToast(ProfileActivity.this, "Please enter all fields");
        }

    }
}