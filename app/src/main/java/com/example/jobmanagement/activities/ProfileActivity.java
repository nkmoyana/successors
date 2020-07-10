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

        edtEmail.setHint(R.string.empty_string);
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    edtEmail.setHint(R.string.enter_email_address);
                }
                else
                {
                    edtEmail.setHint(R.string.empty_string);
                }
            }
        });

        edtPassword.setHint(R.string.empty_string);
        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    edtPassword.setHint(R.string.please_enter_password);
                }
                else
                {
                    edtPassword.setHint(R.string.empty_string);
                }
            }
        });

        edtConfirm.setHint(R.string.empty_string);
        edtConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    edtConfirm.setHint(R.string.please_enter_password);
                }
                else
                {
                    edtConfirm.setHint(R.string.empty_string);
                }
            }
        });

        edtName.setHint(R.string.empty_string);
        edtName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    edtName.setHint(R.string.please_enter_name);
                }
                else
                {
                    edtName.setHint(R.string.empty_string);
                }
            }
        });

        edtLastName.setHint(R.string.empty_string);
        edtLastName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    edtLastName.setHint(R.string.please_enter_last_name);
                }
                else
                {
                    edtLastName.setHint(R.string.empty_string);
                }
            }
        });

        edtPhone.setHint(R.string.empty_string);
        edtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    edtPhone.setHint(R.string.please_enter_phone_number);
                }
                else
                {
                    edtPhone.setHint(R.string.empty_string);
                }
            }
        });

        edtID.setHint(R.string.empty_string);
        edtID.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    edtID.setHint(R.string.please_enter_identity_number);
                }
                else
                {
                    edtID.setHint(R.string.empty_string);
                }
            }
        });

        dropdownText.setHint(R.string.empty_string);
        dropdownText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    dropdownText.setHint(R.string.select_education_level);
                }
                else
                {
                    dropdownText.setHint(R.string.empty_string);
                }
            }
        });

        dropdownTextField.setHint(R.string.empty_string);
        dropdownTextField.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    dropdownTextField.setHint(R.string.select_field_of_study);
                }
                else
                {
                    dropdownTextField.setHint(R.string.empty_string);
                }
            }
        });


        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = edtEmail.getText().toString();
                validateEmail(email);
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
                String password = edtPassword.getText().toString();
                validatePassword(password);
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
                String id = edtID.getText().toString();
                validateID(id);
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
                String phone = edtPhone.getText().toString();
                validatePhone(phone);
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
    private boolean validateEmail(String email) {
        email = lytEmail.getEditText().getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            lytEmail.setError(getString(R.string.incorrect_email_format));
            return false;
        }
        else
        {
            lytEmail.setError(null);
            lytEmail.setErrorEnabled(false);
            lytEmail.setErrorIconDrawable(R.drawable.error_outline);
            return true;
        }
    }
    private boolean validatePassword(String password) {
        password = lytPassword.getEditText().getText().toString().trim();

        if(!PASSWORD_PATTERN.matcher(password).matches()) {
            lytPassword.setError(getString(R.string.password_too_simple));
            return false;
        }
        else
        {
            lytPassword.setError(null);
            lytPassword.setErrorEnabled(false);
            lytPassword.setErrorIconDrawable(R.drawable.error_outline);
            return true;
        }
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

    private boolean validatePhone(String phone) {
        phone = lytPhone.getEditText().getText().toString().trim();

        if(!PHONE_PATTERN.matcher(phone).matches()) {
            lytPhone.setError(phone + getString(R.string.incorrect_phone_format));
            return false;
        }
        else
        {
            lytPhone.setError(null);
            lytPhone.setErrorEnabled(false);
            lytPhone.setErrorIconDrawable(R.drawable.error_outline);
            return true;
        }
    }

    private boolean validateID(String id) {
        id = lytID.getEditText().getText().toString().trim();

        if(!ID_PATTERN.matcher(id).matches()) {
            lytID.setError(id + getString(R.string.invalid_id));
            return false;
        }
        else
        {
            lytID.setError(null);
            lytID.setErrorEnabled(false);
            lytID.setErrorIconDrawable(R.drawable.error_outline);
            return true;
        }
    }
}