package com.example.jobmanagement.db_repositories.job_profile;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.db_operations.JobProfileDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


public class FindJobProfileAsync extends AsyncTask<String, Void, JobProfile>
{
    private AsyncTaskCallback<JobProfile> callback;
    private Exception exception;
    private JobProfile jobProfile;
    private JobProfileDao jobProfileDao;

    public FindJobProfileAsync (JobProfileDao jobProfileDao, AsyncTaskCallback<JobProfile> callback)
    {
        this.callback = callback;
        this.jobProfileDao = jobProfileDao;
    }

    @Override
    protected JobProfile doInBackground(String... strings) {
        exception = null;

        try
        {
            jobProfile = jobProfileDao.getJobProfileByEmail(strings[0]);

            if (jobProfile == null)
            {
                throw new Exception("Invalid login credentials");
            }
           else
            {
                if(!(jobProfile.getPassword().equals(strings[1]))){
                    throw new Exception("Invalid login credentials");
                }
            }
        }
        catch (Exception e)
        {
            exception = e;
        }

        return jobProfile;
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


