package com.example.jobmanagement.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.util.Patterns;
import android.widget.Button;
import android.text.Editable;
import android.view.ViewGroup;
import android.text.TextUtils;
import java.util.regex.Pattern;
import android.widget.TextView;
import android.text.TextWatcher;
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
import com.example.jobmanagement.db_repositories.job_profile.FindJobProfileAsync;
import com.example.jobmanagement.db_repositories.job_profile.UpdateJobProfileAsync;
import com.example.jobmanagement.db_repositories.job_profile.InsertJobProfileAsync;



public class ProfileActivity extends AppCompatActivity {

    TextInputLayout lytEmail, lytPassword, lytConfirm, lytName, lytLastName, lytPhone, lytID;
    TextInputEditText edtEmail, edtPassword, edtConfirm, edtName, edtLastName, edtPhone, edtID;
    TextView tvTittle;
    TextInputLayout lytQualificationDropdown, lytFieldDropdown;
    AutoCompleteTextView dropdownText, dropdownTextField;
    Button btnRegisterUpdate;
    JobProfileDao jobProfileDao;
    String userEmail, userPassword;

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
            ".{13}" +
            "$");

    private static final Pattern PHONE_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=\\S+$)" +
            ".{10}" +
            "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.register_profile);
        }

        jobProfileDao = Connections.getInstance(ProfileActivity.this).getDatabase().getJobProfileDao();

        btnRegisterUpdate = findViewById(R.id.btnRegister);
        tvTittle = findViewById(R.id.tvTittle);
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

        if(AppUtility.isEdit){
            userEmail = getIntent().getStringExtra("userEmail");
            userPassword = getIntent().getStringExtra("userPassword");

            if(getSupportActionBar() != null){
                getSupportActionBar().setTitle(R.string.update_profile);
            }
            tvTittle.setText(R.string.update_profile);
            btnRegisterUpdate.setText(R.string.update_Title);

            new FindJobProfileAsync(jobProfileDao, new AsyncTaskCallback<JobProfile>() {
                @Override
                public void onSuccess(JobProfile success) {

                    edtEmail.setText(success.getEmail());
                    edtEmail.setEnabled(false);
                    edtPassword.setText(success.getPassword());
                    edtConfirm.setText(success.getPassword());
                    edtName.setText(success.getName());
                    edtLastName.setText(success.getSurname());
                    edtPhone.setText(success.getCellphone());
                    edtID.setText(success.getIdentityNumber());
                    dropdownText.setText(success.getEducation());
                    dropdownTextField.setText(success.getQualification());
                }

                @Override
                public void onException(Exception e) {
                    Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).execute(userEmail, userPassword);

            btnRegisterUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new FindJobProfileAsync(jobProfileDao, new AsyncTaskCallback<JobProfile>() {
                        @Override
                        public void onSuccess(JobProfile success) {

                            String email = getString(R.string.empty_string);
                            if(edtEmail != null && !TextUtils.isEmpty(edtEmail.getText()))
                            {
                                email = edtEmail.getText().toString().trim();
                            }

                            String password = getString(R.string.empty_string);
                            if(edtPassword != null && !TextUtils.isEmpty(edtPassword.getText()))
                            {
                                password = edtPassword.getText().toString().trim();
                            }

                            String name = getString(R.string.empty_string);
                            if(edtName != null && !TextUtils.isEmpty(edtName.getText()))
                            {
                                name = edtName.getText().toString().trim();
                            }

                            String lastName = getString(R.string.empty_string);
                            if(edtLastName != null && !TextUtils.isEmpty(edtLastName.getText()))
                            {
                                lastName = edtLastName.getText().toString().trim();
                            }

                            String phone = getString(R.string.empty_string);
                            if(edtPhone != null && !TextUtils.isEmpty(edtPhone.getText()))
                            {
                                phone = edtPhone.getText().toString().trim();
                            }

                            String id = getString(R.string.empty_string);

                            if(edtID != null && !TextUtils.isEmpty(edtID.getText()))
                            {
                                id = edtID.getText().toString().trim();
                            }

                            success.setEmail(email);
                            success.setPassword(password);
                            success.setName(name);
                            success.setSurname(lastName);
                            success.setCellphone(phone);
                            success.setIdentityNumber(id);
                            success.setEducation(dropdownText.getText().toString());
                            success.setQualification(dropdownTextField.getText().toString());

                            new UpdateJobProfileAsync(jobProfileDao, new AsyncTaskCallback<JobProfile>() {
                                @Override
                                public void onSuccess(JobProfile success) {
                                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                                    AppUtility.ShowToast(ProfileActivity.this, "Hi " +
                                            success.getName() + " " + success.getSurname() +
                                            ", " + "your profile has been updated.", toastView,1);
                                    userEmail = success.getEmail();
                                    userPassword = success.getPassword();
                                    ProfileActivity.this.finish();
                                }

                                @Override
                                public void onException(Exception e) {
                                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                                    AppUtility.ShowToast(ProfileActivity.this, e.getMessage(), toastView,2);
                                }
                            }).execute(success);
                        }

                        @Override
                        public void onException(Exception e) {
                            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).execute(userEmail, userPassword);

                    AppUtility.isEdit = false;
                }
            });
        }

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
                AppUtility.validateInput(lytEmail, edtEmail, Patterns.EMAIL_ADDRESS, getString(R.string.incorrect_email_format));
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
                AppUtility.validateInput(lytPassword, edtPassword,  PASSWORD_PATTERN,getString(R.string.password_too_simple) );
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
                String confirm = getString(R.string.empty_string);

                if(edtConfirm != null && !TextUtils.isEmpty(edtConfirm.getText()))
                {
                    confirm = edtConfirm.getText().toString();
                }

                String password = getString(R.string.empty_string);

                if(edtPassword != null && !TextUtils.isEmpty(edtPassword.getText()))
                {
                    password = edtPassword.getText().toString();
                }

                validateConfirmPassword(confirm, password);
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
            String email = getString(R.string.empty_string);
            if(edtEmail != null && !TextUtils.isEmpty(edtEmail.getText()))
            {
                email = edtEmail.getText().toString().trim();
            }
            jobProfile.setEmail(email);

            String password = getString(R.string.empty_string);
            if(edtPassword != null && !TextUtils.isEmpty(edtPassword.getText()))
            {
                password = edtPassword.getText().toString().trim();
            }
            jobProfile.setPassword(password);

            String name = getString(R.string.empty_string);
            if(edtName != null && !TextUtils.isEmpty(edtName.getText()))
            {
                name = edtName.getText().toString().trim();
            }
            jobProfile.setName(name);

            String lastName = getString(R.string.empty_string);
            if(edtLastName != null && !TextUtils.isEmpty(edtLastName.getText()))
            {
                lastName = edtLastName.getText().toString().trim();
            }
            jobProfile.setSurname(lastName);

            String phone = getString(R.string.empty_string);
            if(edtPhone != null && !TextUtils.isEmpty(edtPhone.getText()))
            {
                phone = edtPhone.getText().toString().trim();
            }
            jobProfile.setCellphone(phone);

            String id = getString(R.string.empty_string);

            if(edtID != null && !TextUtils.isEmpty(edtID.getText()))
            {
                id = edtID.getText().toString().trim();
            }
            jobProfile.setIdentityNumber(id);

            jobProfile.setEducation(dropdownText.getText().toString());
            jobProfile.setQualification(dropdownTextField.getText().toString());

            new InsertJobProfileAsync(jobProfileDao, new AsyncTaskCallback<JobProfile>() {
                @Override
                public void onSuccess(JobProfile success) {
                    //AppUtility.ShowToast(ProfileActivity.this, "Hi "+ success.getName() + " " + success.getSurname() + ", Your profile has been created");

                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                    AppUtility.ShowToast(ProfileActivity.this, getString(R.string.hi) + success.getName() + getString(R.string.empty_string) + success.getSurname() + getString(R.string.profile_created), toastView, 1);

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

            AppUtility.ShowToast(ProfileActivity.this, getString(R.string.enter_all_fields), toastView,2);

        }



    }

    public  void app(){
        AppUtility.validateInput(lytEmail, edtEmail, Patterns.EMAIL_ADDRESS, getString(R.string.incorrect_email_format));
        AppUtility.validateInput(lytPassword, edtPassword, PASSWORD_PATTERN,getString(R.string.password_too_simple) );
        AppUtility.validateNumInput(lytPhone, edtPhone, PHONE_PATTERN, getString(R.string.incorrect_phone_format));
        AppUtility.validateNumInput(lytID, edtID, ID_PATTERN, getString(R.string.invalid_id));
    }

    private void validateConfirmPassword(String confirmPassword, String password) {
        if(edtConfirm != null && !TextUtils.isEmpty(edtConfirm.getText()))
        {
            confirmPassword = edtConfirm.getText().toString().trim();
        }

        if(edtPassword != null && !TextUtils.isEmpty(edtPassword.getText()))
        {
            password = edtPassword.getText().toString().trim();
        }


        if(!confirmPassword.equals(password))
        {
            lytConfirm.setError(getString(R.string.confirmation_unmatched));
        }
        else
        {
            lytConfirm.setError(null);
            lytConfirm.setErrorEnabled(false);
            lytConfirm.setErrorIconDrawable(R.drawable.error_outline);
        }
    }

}