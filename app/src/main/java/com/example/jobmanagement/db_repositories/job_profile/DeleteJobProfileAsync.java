package com.example.jobmanagement.db_repositories.job_profile;

import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobProfile;

import com.example.jobmanagement.db_operations.JobProfileDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;

public class DeleteJobProfileAsync extends AsyncTask<Integer, Void, JobProfile>
{
    private AsyncTaskCallback<JobProfile> callback;
    private Exception exception;
    private JobProfile jobProfile;
    private JobProfileDao jobProfileDao;

    public DeleteJobProfileAsync (JobProfile jobProfile, AsyncTaskCallback<JobProfile> callback)
    {
        this.callback = callback;
        this.jobProfile = jobProfile;
    }

    @Override
    protected JobProfile doInBackground(Integer... integers) {

        exception = null;
        try
        {
            jobProfileDao.delete(this.jobProfile.getId());
        }
        catch (Exception e)
        {
            exception = e;
        }

        return this.jobProfile;
    }

    @Override
    protected void onPostExecute(JobProfile s) {
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


