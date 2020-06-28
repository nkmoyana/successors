package com.example.jobmanagement.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jobmanagement.R;

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

            case R.id.logout:

                startActivity(new Intent(ListAdvertActivity.this, LoginActivity.class));

            case R.id.favorite:

            case R.id.add:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}