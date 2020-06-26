package com.example.jobmanagement.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.example.jobmanagement.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterProfile extends AppCompatActivity {

    TextInputLayout lytEmail, lytPassword, lytConfirm, lytName, lytLastName, lytPhone, lytID;
    TextInputEditText edtEmail, edtPassword, edtConfirm, edtName, edtLastName, edtPhone, edtID;

    TextInputLayout lytQualificationDropdown, lytFieldDropdown;
    AutoCompleteTextView dropdownText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profile);

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

        lytFieldDropdown = findViewById(R.id.lytField_dropdown);
        dropdownText = findViewById(R.id.dropdown_text_field);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(RegisterProfile.this, R.array.education_list, R.layout.menu_dropdown);
        adapter.setDropDownViewResource(R.layout.menu_dropdown);
        dropdownText.setAdapter(adapter);

        /*sprFieldOfStudy = findViewById(R.id.sprFieldOfStudy);
        String[] fList = getResources().getStringArray(R.array.education_list);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt,fList);
        sprFieldOfStudy.setAdapter(adapter2);*/
    }
}