package com.example.jobmanagement.db_repositories.job_application;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobApplication;
import com.example.jobmanagement.db_operations.JobApplicationDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


public class InsertJobApplicationAsync extends AsyncTask<Integer, Void, JobApplication>
{
    private Context context;
    private AsyncTaskCallback<JobApplication> callback;
    private Exception exception;
    private JobApplication jobApplication;
    private JobApplicationDao jobApplicationDao;

    public InsertJobApplicationAsync (JobApplication jobApplication, AsyncTaskCallback<JobApplication> callback)
    {
        this.callback = callback;
        this.jobApplication = jobApplication;
    }

    @Override
    protected JobApplication doInBackground(Integer... integers) {

        exception = null;

        try
        {
            if(this.jobApplication == null)
            {
                jobApplicationDao.insert(this.jobApplication);
            }
            else
            {
                throw new Exception("Job Application already exists!");
            }
        }
        catch (Exception e)
        {
            exception = e;
        }

        return this.jobApplication;
    }

    @Override
    protected void onPostExecute(JobApplication s) {
        super.onPostExecute(s);

        if (callback != null)
        {
            if (exception == null)
            {
                callback.onSuccess(s);
            }
            else
            {
                callback.onException(exception);
            }
        }
    }
}
