package com.example.jobmanagement.db_repositories.job_advert;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.db_operations.JobAdvertDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


public class InsertJobAdvertAsync extends AsyncTask<Integer, Void, JobAdvert>
{
    private Context context;
    private AsyncTaskCallback<JobAdvert> callback;
    private Exception exception;
    private JobAdvert jobAdvert;
    private JobAdvertDao jobAdvertDao;

    public InsertJobAdvertAsync (JobAdvert jobAdvert, AsyncTaskCallback<JobAdvert> callback)
    {
        this.callback = callback;
        this.jobAdvert = jobAdvert;
    }

    @Override
    protected JobAdvert doInBackground(Integer... integers) {

        exception = null;
        JobAdvert jobAdvert;
        jobAdvert = jobAdvertDao.getJobAdvertById(this.jobAdvert.getId());
        try
        {
            if(jobAdvert == null)
            {
               jobAdvertDao.insert(this.jobAdvert);
            }
            else
            {
                throw new Exception("Job Advert already exists!");
            }
        }
        catch (Exception e)
        {
            exception = e;
        }

        return this.jobAdvert;
    }

    @Override
    protected void onPostExecute(JobAdvert s) {
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

