package com.example.jobmanagement.db_repositories.job_profile;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.db_operations.JobProfileDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


public class InsertJobProfileAsync extends AsyncTask<Integer, Void, JobProfile>
{
    private Context context;
    private AsyncTaskCallback<JobProfile> callback;
    private Exception exception;
    private JobProfile jobProfile;
    private JobProfileDao jobProfileDao;

    public InsertJobProfileAsync (JobProfile jobProfile, AsyncTaskCallback<JobProfile> callback)
    {
        this.callback = callback;
        this.jobProfile = jobProfile;
    }

    @Override
    protected JobProfile doInBackground(Integer... integers) {

        exception = null;


       // JobProfile jobProfile = jobProfileDao.getJobProfileById(this.jobProfile.getId());

        try
        {
//            if(jobProfile == null)
//            {
//               jobProfileDao.insert(this.jobProfile);
//            }
//            else
//            {
//                throw new Exception("Job Profile already exists!");
//            }
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

