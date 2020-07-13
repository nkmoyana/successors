package com.example.jobmanagement.db_repositories.job_profile;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.db_operations.JobProfileDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


public class UpdateJobProfileAsync extends AsyncTask<JobProfile, Void, JobProfile>
{
    private AsyncTaskCallback<JobProfile> callback;
    private Exception exception;
    private JobProfileDao jobProfileDao;

    public UpdateJobProfileAsync (JobProfileDao jobProfileDao, AsyncTaskCallback<JobProfile> callback)
    {
        this.callback = callback;
        this.jobProfileDao = jobProfileDao;
    }

    @Override
    protected JobProfile doInBackground(JobProfile... jobProfiles) {

        exception = null;
        try
        {
            jobProfileDao.update(jobProfiles[0]);
        }
        catch (Exception e)
        {
            exception = e;
        }

        return jobProfiles[0];
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

