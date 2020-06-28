package com.example.jobmanagement.db_repositories.job_application;

import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobApplication;
import com.example.jobmanagement.db_operations.JobApplicationDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


public class DeleteJobApplicationAsync extends AsyncTask<Integer, Void, JobApplication>
{
    private AsyncTaskCallback<JobApplication> callback;
    private Exception exception;
    private JobApplication jobApplication;
    private JobApplicationDao jobApplicationDao;

    public DeleteJobApplicationAsync (JobApplication jobApplication, AsyncTaskCallback<JobApplication> callback)
    {
        this.callback = callback;
        this.jobApplication = jobApplication;
    }

    @Override
    protected JobApplication doInBackground(Integer... integers) {

        exception = null;
        try
        {
            jobApplicationDao.delete(jobApplication);
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




