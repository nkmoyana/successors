package com.example.jobmanagement.db_repositories.job_advert;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.db_operations.JobAdvertDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


public class UpdateJobAdvertAsync extends AsyncTask<Integer, Void, JobAdvert>
{
    private Context context;
    private AsyncTaskCallback<JobAdvert> callback;
    private Exception exception;
    private JobAdvert jobAdvert;
    private JobAdvertDao jobAdvertDao;

    public UpdateJobAdvertAsync (JobAdvert jobAdvert, AsyncTaskCallback<JobAdvert> callback)
    {
        this.callback = callback;
        this.jobAdvert = jobAdvert;
    }

    @Override
    protected JobAdvert doInBackground(Integer... integers) {

        exception = null;

        try
        {
            jobAdvertDao.update(this.jobAdvert);
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

