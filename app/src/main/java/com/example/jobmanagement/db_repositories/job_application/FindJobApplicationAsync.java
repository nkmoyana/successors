package com.example.jobmanagement.db_repositories.job_application;

import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobApplication;
import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.db_operations.JobApplicationDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


public class FindJobApplicationAsync extends AsyncTask<Integer, Void, JobApplication>
{
    private AsyncTaskCallback<JobApplication> callback;
    private Exception exception;
    private JobApplication jobApplication;
    private JobApplicationDao jobApplicationDao;
    private JobProfile jobProfile;

    public FindJobApplicationAsync (JobApplication jobApplication,
                                    JobProfile jobProfile,
                                    AsyncTaskCallback<JobApplication> callback)
    {
        this.callback = callback;
        this.jobApplication = jobApplication;
        this.jobProfile = jobProfile;
    }

    @Override
    protected JobApplication doInBackground(Integer... integers) {

        exception = null;



        try
        {
            if (jobApplicationDao.getAllJobApplications().size() != 0)
            {
                jobApplicationDao.findByJobIdAndUserId(this.jobApplication.getJobId(),
                        this.jobProfile.getId());
            }
            else
            {
                throw new Exception("There is no data in the database");
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




