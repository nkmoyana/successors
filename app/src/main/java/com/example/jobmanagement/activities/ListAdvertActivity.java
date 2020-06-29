package com.example.jobmanagement.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jobmanagement.R;
import com.example.jobmanagement.app_utilities.AppUtility;

public class ListAdvertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_advert);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Job Adverts");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.editProfile:

                Intent intent = new Intent(ListAdvertActivity.this, EditProfileActivity.class);
                String email = AppUtility.sharedpreferences.getString("email", "");
                intent.putExtra("email", email);
                startActivity(intent);
            case R.id.logout:
                AppUtility.sharedpreferences.edit().putString("email", "").apply();
                AppUtility.sharedpreferences.edit().putString("password", "").apply();
                startActivity(new Intent(ListAdvertActivity.this, LoginActivity.class));
                ListAdvertActivity.this.finish();
            case R.id.favorite:

            case R.id.add:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}