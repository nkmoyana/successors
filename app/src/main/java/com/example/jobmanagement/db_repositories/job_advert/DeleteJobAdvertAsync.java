package com.example.jobmanagement.db_repositories.job_advert;

import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.db_operations.JobAdvertDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;

public class DeleteJobAdvertAsync extends AsyncTask<JobAdvert, Void, JobAdvert>
{
    private AsyncTaskCallback<JobAdvert> callback;
    private Exception exception;
    private JobAdvert jobAdvert;
    private JobAdvertDao jobAdvertDao;

    public DeleteJobAdvertAsync (JobAdvertDao jobAdvertDao, AsyncTaskCallback<JobAdvert> callback)
    {
        this.callback = callback;
        this.jobAdvertDao = jobAdvertDao;
    }

    @Override
    protected JobAdvert doInBackground(JobAdvert... jobAdverts) {

        exception = null;
        try
        {
            jobAdvertDao.delete(jobAdverts[0].getId());
        }
        catch (Exception e)
        {
            exception = e;
        }

        return jobAdverts[0];
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



