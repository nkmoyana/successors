package com.example.jobmanagement.db_repositories.job_advert;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.db_operations.JobAdvertDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;


import java.util.List;

public class GetAllJobAdvertsAsync extends AsyncTask<Integer, Void, List<JobAdvert>>
{
    private Context context;
    private AsyncTaskCallback<List<JobAdvert>> callback;
    private Exception exception;
    private JobAdvertDao jobAdvertDao;

    public GetAllJobAdvertsAsync (AsyncTaskCallback<List<JobAdvert>> callback)
    {
        this.callback = callback;
    }

    @Override
    protected List<JobAdvert> doInBackground(Integer... integers) {

        exception = null;
        List<JobAdvert> results;
        results = jobAdvertDao.getAllJobAdverts();
        try
        {
            if (results.size() == 0)
            {
                throw new Exception("There is no data in the database");
            }
        }
        catch (Exception e)
        {
            exception = e;
        }

        return results;
    }

    @Override
    protected void onPostExecute(List<JobAdvert> s) {
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

