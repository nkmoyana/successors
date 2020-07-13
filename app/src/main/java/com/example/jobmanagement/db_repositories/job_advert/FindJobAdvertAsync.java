package com.example.jobmanagement.db_repositories.job_advert;

import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.db_operations.JobAdvertDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;

public class FindJobAdvertAsync extends AsyncTask<Long, Void, JobAdvert>
{
    private AsyncTaskCallback<JobAdvert> callback;
    private Exception exception;
    private JobAdvert jobAdvert;
    private JobAdvertDao jobAdvertDao;

    public FindJobAdvertAsync (JobAdvertDao jobAdvertDao, JobAdvert jobAdvert, AsyncTaskCallback<JobAdvert> callback)
    {
        this.callback = callback;
        this.jobAdvert = jobAdvert;
        this.jobAdvertDao = jobAdvertDao;
    }

    @Override
    protected JobAdvert doInBackground(Long... longs) {

        exception = null;
        try
        {
            if (jobAdvertDao.getAllJobAdverts().size() != 0)
            {
                jobAdvert = jobAdvertDao.getJobAdvertById(longs[0]);
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

        return jobAdvert;
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



