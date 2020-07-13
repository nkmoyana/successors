package com.example.jobmanagement.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.view.ViewGroup;
import android.content.Intent;
import com.example.jobmanagement.R;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.app_utilities.AppUtility;
import com.example.jobmanagement.db_operations.Connections;
import com.example.jobmanagement.db_operations.JobAdvertDao;
import com.example.jobmanagement.db_repositories.job_advert.FindJobAdvertAsync;
import com.example.jobmanagement.db_repositories.job_advert.UpdateJobAdvertAsync;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;
import com.example.jobmanagement.db_repositories.job_advert.InsertJobAdvertAsync;



public class JobAdvertActivity extends AppCompatActivity {

    TextInputLayout lytJobTitle, lytAppointType, lytJobPosition, lytJobLocation,lytAdCompany,
            lytJobDescription, lytMinQualification, lytSalary;

    TextInputEditText edtJobTitle, edtAdCompany, edtJobDescription, edtSalary;

    AutoCompleteTextView dropdownAppointType, dropdownPosition, dropdownLocation,dropdownQualification;

    Switch swLicense;

    JobAdvert jobAdvert;

    JobAdvertDao jobAdvertDao;

    Boolean licenseFlag;

    Button btnSaveRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_advert);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.insert_job_advert);
        }

        jobAdvert = new JobAdvert();
        jobAdvertDao = Connections.getInstance(JobAdvertActivity.this).getDatabase().getJobAdvertDao();

        btnSaveRegister = findViewById(R.id.btnSave);
        lytJobTitle = findViewById(R.id.lytJobTitle);
        lytAppointType = findViewById(R.id.lytAppointmentType);
        lytJobPosition = findViewById(R.id.lytJobPosition);
        lytJobLocation = findViewById(R.id.lytJobLocation);
        lytAdCompany = findViewById(R.id.lytAdCompany);
        lytJobDescription = findViewById(R.id.lytJobDescription);
        lytMinQualification = findViewById(R.id.lytMinQualification);
        lytSalary = findViewById(R.id.lytSalary);

        edtJobTitle = findViewById(R.id.edtJobTitle);
        edtAdCompany = findViewById(R.id.edtAdCompany);
        edtJobDescription = findViewById(R.id.edtJobDescription);
        edtSalary = findViewById(R.id.edtSalary);

        dropdownAppointType = findViewById(R.id.dropdown_appoint_type);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(JobAdvertActivity.this, R.array.appointment_list, R.layout.menu_dropdown);
        adapter1.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownAppointType.setAdapter(adapter1);

        dropdownLocation = findViewById(R.id.dropdown_job_location);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(JobAdvertActivity.this, R.array.job_location_list, R.layout.menu_dropdown);
        adapter2.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownLocation.setAdapter(adapter2);

        dropdownPosition = findViewById(R.id.dropdown_job_position);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(JobAdvertActivity.this, R.array.job_position_list, R.layout.menu_dropdown);
        adapter3.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownPosition.setAdapter(adapter3);

        dropdownQualification = findViewById(R.id.dropdown_min_qualification);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(JobAdvertActivity.this, R.array.qualification_list, R.layout.menu_dropdown);
        adapter.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownQualification.setAdapter(adapter);

        swLicense = findViewById(R.id.swLicense);

        licenseFlag = false;

        //AppUtility.sharedpreferences = getSharedPreferences("licensePreference", MODE_PRIVATE);

        if(AppUtility.isEdit){
            if(getSupportActionBar() != null){
                getSupportActionBar().setTitle(R.string.update_JobAvert_ActionBar);
            }
            btnSaveRegister.setText(R.string.update_Title);

            new FindJobAdvertAsync(jobAdvertDao, jobAdvert, new AsyncTaskCallback<JobAdvert>() {
                @Override
                public void onSuccess(JobAdvert success) {

                    //TESTING PURPOSE
                    if(success == null){
                        Toast.makeText(JobAdvertActivity.this, "success is null", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        edtJobTitle.setText(success.getJobTitle());
                        dropdownAppointType.setText(success.getAppointmentType());
                        dropdownPosition.setText(success.getJobPosition());
                        dropdownLocation.setText(success.getJobLocation());
                        edtAdCompany.setText(success.getJobCompany());
                        edtJobDescription.setText(success.getJobDescription());
                        dropdownQualification.setText(success.getJobQualification());
                        edtSalary.setText(success.getJobSalary());
                        swLicense.setChecked(true);
                    }//end else
                }

                @Override
                public void onException(Exception e) {
                    Toast.makeText(JobAdvertActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).execute(getIntent().getLongExtra("JobAvertId", 0));

//            btnSaveRegister.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    new FindJobAdvertAsync(jobAdvertDao, jobAdvert, new AsyncTaskCallback<JobAdvert>() {
//                        @Override
//                        public void onSuccess(JobAdvert success) {
//                            success.setJobTitle(edtJobTitle.getText().toString().trim());
//                            success.setAppointmentType(dropdownAppointType.getText().toString().trim());
//                            success.setJobPosition(dropdownPosition.getText().toString().trim());
//                            success.setJobLocation(dropdownLocation.getText().toString().trim());
//                            success.setJobCompany(edtAdCompany.getText().toString().trim());
//                            success.setJobDescription(edtJobDescription.getText().toString().trim());
//                            success.setJobQualification(dropdownQualification.getText().toString());
//                            success.setJobSalary(edtSalary.getText().toString());
//                            success.setLicence(true);
//
//                            new UpdateJobProfileAsync(jobAdvertDao, new AsyncTaskCallback<JobProfile>() {
//                                @Override
//                                public void onSuccess(jobAdvertDao success) {
//                                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));
//
//                                    AppUtility.ShowToast(JobAdvertActivity.this, "Hi " +
//                                            success.getName() + " " + success.getSurname() +
//                                            ", " + "your profile has been updated.", toastView,1);
//                                    userEmail = success.getEmail();
//                                    userPassword = success.getPassword();
//                                    JobAdvertActivity.this.finish();
//                                }
//
//                                @Override
//                                public void onException(Exception e) {
//                                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));
//
//                                    AppUtility.ShowToast(JobAdvertActivity.this, e.getMessage(), toastView,2);
//                                }
//                            }).execute(success);
//                        }
//
//                        @Override
//                        public void onException(Exception e) {
//                            Toast.makeText(JobAdvertActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }).execute(userEmail, userPassword);
//
//                    AppUtility.isEdit = false;
//                }
//            });

        } // end If

        swLicense.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    licenseFlag = true;
                }
                    licenseFlag = false;

            }

        });

        AppUtility.setOnFocusChangeListener(edtJobTitle,"Job Title");
        AppUtility.setOnFocusChangeListener(edtAdCompany,"Advertising Company");
        AppUtility.setOnFocusChangeListener(edtJobDescription,"Job Description");
        AppUtility.setOnFocusChangeListener(edtSalary,"Monthly Gross Salary");

    }
    public void Save(View view) {
        jobAdvert.setJobTitle(edtJobTitle.getText().toString());
        jobAdvert.setAppointmentType(dropdownAppointType.getText().toString());
        jobAdvert.setJobPosition(dropdownPosition.getText().toString());
        jobAdvert.setJobLocation(dropdownLocation.getText().toString());
        jobAdvert.setJobCompany(edtAdCompany.getText().toString());
        jobAdvert.setJobDescription(edtJobDescription.getText().toString());
        jobAdvert.setJobQualification(dropdownQualification.getText().toString());
        jobAdvert.setJobSalary(edtSalary.getText().toString());
        jobAdvert.setLicence(licenseFlag);


        new InsertJobAdvertAsync(jobAdvertDao, new AsyncTaskCallback<JobAdvert>() {
            @Override
            public void onSuccess(JobAdvert success) {

                View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                AppUtility.ShowToast(getApplicationContext(), success.getJobCompany() + " successfully added!", toastView,1);

//                Toast.makeText(getApplicationContext(), success.getJobCompany() +
//                        " successfully added!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onException(Exception e) {
                if (e.getMessage() == "Job Advert already exist")
                {
                    new UpdateJobAdvertAsync(jobAdvertDao, new AsyncTaskCallback<JobAdvert>() {
                        @Override
                        public void onSuccess(JobAdvert success) {
                            //toast display
                            Intent intent = new Intent();
                            intent.putExtra("data", jobAdvert);
                            setResult(RESULT_OK, intent);
                            Toast.makeText(getApplicationContext(), success.getJobCompany() +
                                    " successfully updated!", Toast.LENGTH_SHORT).show();
                            JobAdvertActivity.this.finish();

                        }

                        @Override
                        public void onException(Exception e) {
                            View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                            AppUtility.ShowToast(JobAdvertActivity.this, e.getMessage(), toastView,2);
                        }
                    }).execute(jobAdvert);
                }
                else
                {
                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                    AppUtility.ShowToast(JobAdvertActivity.this, e.getMessage(), toastView,2);
                }
            }
        }).execute(jobAdvert);
//        String jTitle = edtJobTitle.getText().toString();
//        String salary = edtSalary.getText().toString();
//        String aType = dropdownAppointType.getText().toString();
//        String jPosition = dropdownPosition.getText().toString();
//        String jLocation = dropdownLocation.getText().toString();
//        String jCompany = edtAdCompany.getText().toString();
//        String jDescription = edtJobDescription.getText().toString();
//        String jQualification = dropdownQualification.getText().toString();
//

//        if (swLicense.isChecked())
//        {
//            jobAdvert.setLicence(true);
//        }
//        else
//        {
//            jobAdvert.setLicence(false);
//        }

//        jobAdvert = new JobAdvert(jTitle, salary, jLocation,
//                                     aType, jPosition, jCompany, jDescription,
//                                            licenseFlag, jQualification);



    }
}