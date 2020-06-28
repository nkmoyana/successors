package com.example.jobmanagement.db_repositories.job_application;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobApplication;
import com.example.jobmanagement.db_operations.JobApplicationDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


import java.util.List;

public class GetAllApplicationsAsync extends AsyncTask<Integer, Void, List<JobApplication>>
{
    private Context context;
    private AsyncTaskCallback<List<JobApplication>> callback;
    private Exception exception;
    private JobApplicationDao jobAdvertDao;

    public GetAllApplicationsAsync (AsyncTaskCallback<List<JobApplication>> callback)
    {
        this.callback = callback;
    }

    @Override
    protected List<JobApplication> doInBackground(Integer... integers) {

        exception = null;
        List<JobApplication> results;
        results = jobAdvertDao.getAllJobApplications();
        try
        {
            if (results.size() == 0)
            {
                throw new Exception("There is no data in the database");
            }
        }
        catch (Exception e)
        {
            exception = e;
        }

        return results;
    }

    @Override
    protected void onPostExecute(List<JobApplication> s) {
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


