package com.example.jobmanagement.activities;


import android.os.Bundle;
import android.widget.TextView;
import com.example.jobmanagement.R;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jobmanagement.app_utilities.ApplicationClass;

public class JobDetailActivity extends AppCompatActivity {

    TextView tvJobTitle, tvJobLocation, tvAdCompany;

    TextView tvMinQualification, tvSalary, tvAppointment;

    TextView tvPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        tvJobTitle = findViewById(R.id.tvJobTitle);
        tvJobLocation = findViewById(R.id.tvJobLocation);
        tvAdCompany = findViewById(R.id.tvAdCompany);

        tvMinQualification = findViewById(R.id.tvMinQualification);
        tvSalary = findViewById(R.id.tvSalary);
        tvAppointment = findViewById(R.id.tvAppointment);

        tvPosition = findViewById(R.id.tvPosition);

        int i = getIntent().getIntExtra("index", 0);

        tvJobTitle.setText(ApplicationClass.jobAdverts.get(i).getJobTitle());
        tvJobLocation.setText(ApplicationClass.jobAdverts.get(i).getJobLocation());
        tvAdCompany.setText(ApplicationClass.jobAdverts.get(i).getJobCompany());

        tvMinQualification.setText(ApplicationClass.jobAdverts.get(i).getJobQualification());
        tvSalary.setText(ApplicationClass.jobAdverts.get(i).getJobSalary());
        tvAppointment.setText(ApplicationClass.jobAdverts.get(i).getAppointmentType());

        tvPosition.setText(ApplicationClass.jobAdverts.get(i).getJobPosition());

    }
}