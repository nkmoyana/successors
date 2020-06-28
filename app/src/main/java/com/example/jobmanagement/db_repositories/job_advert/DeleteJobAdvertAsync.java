package com.example.jobmanagement.db_repositories.job_advert;

import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.db_operations.JobAdvertDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;

public class DeleteJobAdvertAsync extends AsyncTask<Integer, Void, JobAdvert>
{
    private AsyncTaskCallback<JobAdvert> callback;
    private Exception exception;
    private JobAdvert jobAdvert;
    private JobAdvertDao jobAdvertDao;

    public DeleteJobAdvertAsync (JobAdvert jobAdvert, AsyncTaskCallback<JobAdvert> callback)
    {
        this.callback = callback;
        this.jobAdvert = jobAdvert;
    }

    @Override
    protected JobAdvert doInBackground(Integer... integers) {

        exception = null;
        try
        {
            jobAdvertDao.delete(this.jobAdvert.getId());
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



