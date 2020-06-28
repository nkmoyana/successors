package com.example.jobmanagement.db_repositories.job_application;

import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobApplication;
import com.example.jobmanagement.db_operations.JobApplicationDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


import java.util.List;

public class GetUserApplicationsAsync extends AsyncTask<Integer, Void, List<JobApplication>>
{
    private AsyncTaskCallback<List<JobApplication>> callback;
    private Exception exception;
    private JobApplication jobApplication;
    private JobApplicationDao jobApplicationDao;

    public GetUserApplicationsAsync (JobApplication jobApplication,
                                     AsyncTaskCallback<List<JobApplication>> callback)
    {
        this.callback = callback;
        this.jobApplication = jobApplication;
    }

    @Override
    protected List<JobApplication> doInBackground(Integer... integers) {

        exception = null;

        List<JobApplication> jobApplications;

        jobApplications = jobApplicationDao.getUserApplications(this.jobApplication.getJobId());

        try
        {
            if (jobApplicationDao.getAllJobApplications().size() == 0)
            {
                throw new Exception("There is no data in the database");
            }
        }
        catch (Exception e)
        {
            exception = e;
        }

        return jobApplications;
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
