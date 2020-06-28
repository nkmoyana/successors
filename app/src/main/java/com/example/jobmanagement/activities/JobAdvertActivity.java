package com.example.jobmanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Switch;

import com.example.jobmanagement.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class JobAdvertActivity extends AppCompatActivity {

    TextInputLayout lytJobTitle, lytAppointType, lytJobPosition, lytJobLocation,lytAdCompany,
            lytJobDescription, lytMinQualification, lytSalary;

    TextInputEditText edtJobTitle, edtAdCompany, edtJobDescription, edtSalary;

    AutoCompleteTextView dropdownAppointType, dropdownPosition, dropdownLocation,dropdownQualification;

    Switch swLicense;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_advert);

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



    }
}