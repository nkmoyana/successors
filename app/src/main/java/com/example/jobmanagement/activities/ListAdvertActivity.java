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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.example.jobmanagement.db_repositories.job_advert.GetAllJobAdvertsAsync;
import com.example.jobmanagement.db_repositories.job_application.InsertJobApplicationAsync;

import java.util.ArrayList;
import java.util.List;

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


            new GetAllJobAdvertsAsync(jobAdvertDao ,new AsyncTaskCallback<List<JobAdvert>>() {
                @Override
                public void onSuccess(List<JobAdvert> success) {
//
                    if (success.size() != 0)
                    {
                        ApplicationClass.jobAdverts = (ArrayList)success;
                        layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new DataAdapter(success);
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onException(Exception e) {
                        //Toast Display

                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                    AppUtility.ShowToast(ListAdvertActivity.this, "Error: ", toastView,2);

//                    Toast.makeText(ListAdvertActivity.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).execute();

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
                break;
            case R.id.logout:
                AppUtility.sharedpreferences.edit().putString("email", "").apply();
                AppUtility.sharedpreferences.edit().putString("password", "").apply();
                startActivity(new Intent(ListAdvertActivity.this, LoginActivity.class));
                ListAdvertActivity.this.finish();
                break;
            case R.id.favorite:
            case R.id.add:
                Intent addIntent = new Intent(ListAdvertActivity.this, JobAdvertActivity.class);
                addIntent.putExtra("requestCode", INSERT_JOB_ADVERT);
                startActivityForResult(addIntent, INSERT_JOB_ADVERT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEditAdvertClick(JobAdvert jobAdvert) {
        Intent intent = new Intent(ListAdvertActivity.this, JobAdvertActivity.class);
        intent.putExtra("requestCode", UPDATE_JOB_ADVERT);
        startActivityForResult(intent, UPDATE_JOB_ADVERT);
    }

    @Override
    public void onViewAdvertClick(int i) {
        Intent intent = new Intent(ListAdvertActivity.this, JobDetailActivity.class);
        intent.putExtra("index", i);
        startActivity(intent);
    }

    @Override
    public void onDeleteAdvertClick(JobAdvert jobAdvert) {
        new DeleteJobAdvertAsync(jobAdvertDao, new AsyncTaskCallback<JobAdvert>() {
            @Override
            public void onSuccess(JobAdvert success) {

                View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                AppUtility.ShowToast(ListAdvertActivity.this, " successfully deleted!", toastView,3);

//                Toast.makeText(ListAdvertActivity.this,
//                        success.getJobTitle() + " successfully deleted!",
//                        Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
//                AppUtility.ShowToast(ListAdvertActivity.this, success.getJobTitle()
//                        + "successfully deleted!!!");
            }

            @Override
            public void onException(Exception e) {

                View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                AppUtility.ShowToast(ListAdvertActivity.this, "Error: ", toastView,2);

//                Toast.makeText(ListAdvertActivity.this, "Error : " +
//                        e.getMessage(), Toast.LENGTH_SHORT).show();
//                    AppUtility.ShowToast(ListAdvertActivity.this, "Error : " +
//                                e.getMessage());
            }
        }).execute(jobAdvert);
    }

    @Override
    public void onJobApplicationClick(JobApplication jobApplication) {
        new InsertJobApplicationAsync(jobApplication, new AsyncTaskCallback<JobApplication>() {
            @Override
            public void onSuccess(JobApplication success) {

                View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                AppUtility.ShowToast(ListAdvertActivity.this, "Job Advert successfully applied to", toastView,1);

//                Toast.makeText(ListAdvertActivity.this, "Job Advert successfully applied to",
//                        Toast.LENGTH_SHORT).show();
//                AppUtility.ShowToast(ListAdvertActivity.this, success.getProfileId()
//                        + "successfully added!!!");
            }

            @Override
            public void onException(Exception e) {

                View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLinLay));

                AppUtility.ShowToast(ListAdvertActivity.this, "Error: ", toastView,2);

//                Toast.makeText(ListAdvertActivity.this, "Error : " +
//                        e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }).execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == INSERT_JOB_ADVERT && resultCode == RESULT_OK)
        {
            //JobAdvert dataBack = data.getParcelableExtra("data");

           // ApplicationClass.jobAdverts.add(dataBack);

            adapter.notifyDataSetChanged();
        }
        else
        {
            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}