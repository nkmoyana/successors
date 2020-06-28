package com.example.jobmanagement.db_repositories.job_application;

import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.data_models.JobApplication;
import com.example.jobmanagement.db_operations.JobApplicationDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


import java.util.List;

public class GetUserJobAdvertsAsync extends AsyncTask<Integer, Void, List<JobAdvert>>
{
    private AsyncTaskCallback<List<JobAdvert>> callback;
    private Exception exception;
    JobApplication jobApplication;
    private JobApplicationDao jobApplicationDao;

    public GetUserJobAdvertsAsync (AsyncTaskCallback<List<JobAdvert>> callback)
    {
        this.callback = callback;
        this.jobApplication = jobApplication;
    }

    @Override
    protected List<JobAdvert> doInBackground(Integer... integers) {

        exception = null;

        List<JobAdvert> jobApplications;

        jobApplications = jobApplicationDao.getUserAppliedJobAdverts(
                                            this.jobApplication.getJobId());

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
    protected void onPostExecute(List<JobAdvert> s) {
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

