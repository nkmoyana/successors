package com.example.jobmanagement.db_repositories.job_profile;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.db_operations.JobProfileDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


public class InsertJobProfileAsync extends AsyncTask<JobProfile, Void, JobProfile>
{
    private AsyncTaskCallback<JobProfile> callback;
    private Exception exception;
    private JobProfile jobProfile;
    private JobProfileDao jobProfileDao;

    public InsertJobProfileAsync (JobProfileDao jobProfileDao , AsyncTaskCallback<JobProfile> callback)
    {
        this.callback = callback;
        this.jobProfileDao = jobProfileDao;
    }

    @Override
    protected JobProfile doInBackground(JobProfile... jobProfiles) {

        exception = null;
        try
        {
            jobProfile = jobProfileDao.getJobProfileByEmail(jobProfiles[0].getEmail()); //Check to see if the object already exist
            if(jobProfile == null)
            {
                jobProfileDao.insert(jobProfiles[0]);
            }
            else
            {
                throw new Exception("Job Profile already exists!");
            }
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

