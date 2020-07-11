package com.example.jobmanagement.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import java.util.regex.Pattern;
import com.example.jobmanagement.R;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.app_utilities.AppUtility;
import com.example.jobmanagement.db_operations.Connections;
import com.example.jobmanagement.db_operations.JobProfileDao;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;
import com.example.jobmanagement.db_repositories.job_profile.InsertJobProfileAsync;



public class ProfileActivity extends AppCompatActivity {

    TextInputLayout lytEmail, lytPassword, lytConfirm, lytName, lytLastName, lytPhone, lytID;
    TextInputEditText edtEmail, edtPassword, edtConfirm, edtName, edtLastName, edtPhone, edtID;

    TextInputLayout lytQualificationDropdown, lytFieldDropdown;
    AutoCompleteTextView dropdownText, dropdownTextField;

    JobProfileDao jobProfileDao;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[@#$%^&+=])" +
            "(?=\\S+$)" +
            ".{8,}" +
            "$");

    private static final Pattern ID_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=\\S+$)" +
            ".{13,13}" +
            "$");

    private static final Pattern PHONE_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=\\S+$)" +
            ".{10,10}" +
            "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.register_profile);
        }

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

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.qualification_list, R.layout.menu_dropdown);
        adapter1.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownText.setAdapter(adapter1);

        lytFieldDropdown = findViewById(R.id.lytField_dropdown);
        dropdownTextField = findViewById(R.id.dropdown_text_field);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.education_list, R.layout.menu_dropdown);
        adapter.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownTextField.setAdapter(adapter);

        AppUtility.setOnFocusChangeListener(edtEmail,getString(R.string.email));
        AppUtility.setOnFocusChangeListener(edtPassword,getString(R.string.password));
        AppUtility.setOnFocusChangeListener(edtConfirm,getString(R.string.confirm_password));
        AppUtility.setOnFocusChangeListener(edtName,getString(R.string.name));
        AppUtility.setOnFocusChangeListener(edtLastName,getString(R.string.last_name));
        AppUtility.setOnFocusChangeListener(edtPhone,getString(R.string.phone_number));
        AppUtility.setOnFocusChangeListener(edtID,getString(R.string.identity_number));
        AppUtility.setDropdownText(dropdownText,getString(R.string.education_level));
        AppUtility.setDropdownText(dropdownTextField,getString(R.string.field_of_study));



        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
//                String email = edtEmail.getText().toString();
                AppUtility.validateInput(lytEmail, Patterns.EMAIL_ADDRESS, getString(R.string.incorrect_email_format));
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                //String password = edtPassword.getText().toString();
                //if(!edtPassword.isFocused())
                //{
                AppUtility.validateInput(lytPassword, PASSWORD_PATTERN,getString(R.string.password_too_simple) );
                //}
            }
        });

        edtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String confirm = edtConfirm.getText().toString();
                String password = edtPassword.getText().toString();
                validateConfirmPassword(confirm,password);
            }
        });

        edtID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                //String id = edtID.getText().toString();
                AppUtility.validateNumInput(lytID, edtID, ID_PATTERN, getString(R.string.invalid_id));
            }
        });

        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                //String phone = edtPhone.getText().toString();
                AppUtility.validateNumInput(lytPhone, edtPhone, PHONE_PATTERN, getString(R.string.incorrect_phone_format));
            }
        });



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
                    //AppUtility.ShowToast(ProfileActivity.this, "Hi "+ success.getName() + " " + success.getSurname() + ", Your profile has been created");

                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                    AppUtility.ShowToast(ProfileActivity.this, "Hi " + success.getName() + " " + success.getSurname() + ", Your profile has been created", toastView, 1);

                    ProfileActivity.this.finish();
                }

                @Override
                public void onException(Exception e) {
                    //AppUtility.ShowToast(ProfileActivity.this, e.getMessage());

                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                    AppUtility.ShowToast(ProfileActivity.this, e.getMessage(), toastView,2);

                }
            }).execute(jobProfile);
        }
        else {
            //AppUtility.ShowToast(ProfileActivity.this, "Please enter all fields");

            View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

            AppUtility.ShowToast(ProfileActivity.this, "Please enter all fields", toastView,2);

        }



    }

    public  void app(){
        AppUtility.validateInput(lytEmail, Patterns.EMAIL_ADDRESS, getString(R.string.incorrect_email_format));
        AppUtility.validateInput(lytPassword, PASSWORD_PATTERN,getString(R.string.password_too_simple) );
        AppUtility.validateNumInput(lytPhone, edtPhone, PHONE_PATTERN, getString(R.string.incorrect_phone_format));
        AppUtility.validateNumInput(lytID, edtID, ID_PATTERN, getString(R.string.invalid_id));
    }

    private boolean validateConfirmPassword(String confirmPassword, String password) {
        confirmPassword = lytConfirm.getEditText().getText().toString().trim();
        password = lytPassword.getEditText().getText().toString().trim();

        if(!confirmPassword.equals(password)) {
            lytConfirm.setError(getString(R.string.confirmation_unmatched));
            return false;
        }
        else
        {
            lytConfirm.setError(null);
            lytConfirm.setErrorEnabled(false);
            lytConfirm.setErrorIconDrawable(R.drawable.error_outline);
            return true;
        }
    }

}