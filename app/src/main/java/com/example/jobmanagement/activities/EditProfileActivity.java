package com.example.jobmanagement.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.jobmanagement.R;
import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.db_repositories.job_advert.UpdateJobAdvertAsync;
import com.google.android.material.textfield.TextInputEditText;

public class EditProfileActivity extends AppCompatActivity {

    TextInputEditText edtPassword,edtEmail,edtConfirmPassword,edtFirstName,edtLastName,edtCellNumber,edtId;
    AutoCompleteTextView dropdown_text,dropdown_text_field;
    Button btnRUpdate;

    private JobProfile jobProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edtPassword=findViewById(R.id.edtPassword);
        edtEmail=findViewById(R.id.edtEmail);
        edtConfirmPassword=findViewById(R.id.edtConfirmPassword);
        edtFirstName=findViewById(R.id.edtFirstName);
        edtLastName=findViewById(R.id.edtLastName);
        edtCellNumber=findViewById(R.id.edtCellNr);
        edtId=findViewById(R.id.edtId);
        dropdown_text=findViewById(R.id.dropdown_text);
        dropdown_text_field=findViewById(R.id.dropdown_text_field);
        btnRUpdate=findViewById(R.id.btnRUpdate);




    }

}