package com.example.jobmanagement.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jobmanagement.R;
import com.example.jobmanagement.app_utilities.AppUtility;
import com.example.jobmanagement.app_utilities.ApplicationClass;
import com.example.jobmanagement.app_utilities.CardViewButtonClickListener;
import com.example.jobmanagement.app_utilities.DataAdapter;
import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.data_models.JobApplication;

import com.example.jobmanagement.db_operations.Connections;
import com.example.jobmanagement.db_operations.JobAdvertDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;
import com.example.jobmanagement.db_repositories.job_advert.DeleteJobAdvertAsync;
import com.example.jobmanagement.db_repositories.job_application.InsertJobApplicationAsync;

import java.util.ArrayList;

public class ListAdvertActivity extends AppCompatActivity implements CardViewButtonClickListener
{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    JobAdvertDao jobAdvertDao;

    private final int INSERT_JOB_ADVERT = 1;
    private final int UPDATE_JOB_ADVERT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_advert);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Job Adverts");

        jobAdvertDao = Connections.getInstance(ListAdvertActivity.this).getDatabase().getJobAdvertDao();

        recyclerView = findViewById(R.id.list);

        if (ApplicationClass.jobAdverts != null) {
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new DataAdapter(ApplicationClass.jobAdverts);
            recyclerView.setAdapter(adapter);
        }
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
                startActivityForResult(new Intent(ListAdvertActivity.this, JobAdvertActivity.class),
                        INSERT_JOB_ADVERT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEditAdvertClick(JobAdvert jobAdvert) {
            startActivityForResult(new Intent(ListAdvertActivity.this, JobAdvertActivity.class),
                    UPDATE_JOB_ADVERT);
        jobAdvertDao.update(jobAdvert);
    }

    @Override
    public void onViewAdvertClick(int i) {
        Intent intent = new Intent(ListAdvertActivity.this, JobDetailActivity.class);
        intent.putExtra("index", i);
    }

    @Override
    public void onDeleteAdvertClick(final JobAdvert jobAdvert) {
        new DeleteJobAdvertAsync(jobAdvert, new AsyncTaskCallback<JobAdvert>() {
            @Override
            public void onSuccess(JobAdvert success) {
                AppUtility.ShowToast(ListAdvertActivity.this, success.getJobTitle()
                        + "successfully deleted!!!");
            }

            @Override
            public void onException(Exception e) {
                    AppUtility.ShowToast(ListAdvertActivity.this, "Error : " +
                                e.getMessage());
            }
        }).execute();
    }

    @Override
    public void onJobApplicationClick(JobApplication jobApplication) {
        new InsertJobApplicationAsync(jobApplication, new AsyncTaskCallback<JobApplication>() {
            @Override
            public void onSuccess(JobApplication success) {

                AppUtility.ShowToast(ListAdvertActivity.this, success.getProfileId()
                        + "successfully added!!!");

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onException(Exception e) {
                AppUtility.ShowToast(ListAdvertActivity.this, "Error : " +
                        e.getMessage());
            }
        }).execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK)
        {

            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}