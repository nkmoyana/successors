package com.example.jobmanagement.db_repositories.job_profile;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jobmanagement.data_models.JobProfile;
import com.example.jobmanagement.db_operations.JobProfileDao;
import com.example.jobmanagement.db_repositories.AsyncTaskCallback;

import java.util.List;

public class GetAllJobProfilesAsync extends AsyncTask<Integer, Void, List<JobProfile>>
{
    private Context context;
    private AsyncTaskCallback<List<JobProfile>> callback;
    private Exception exception;
    private JobProfileDao jobProfileDao;

    public GetAllJobProfilesAsync (AsyncTaskCallback<List<JobProfile>> callback)
    {
        this.callback = callback;
    }

    @Override
    protected List<JobProfile> doInBackground(Integer... integers) {

        exception = null;
        List<JobProfile> results;

        results = jobProfileDao.getAllJobProfiles();
        try
        {
            if (results.size() != 0)
            {
                results = jobProfileDao.getAllJobProfiles();
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

        return results;
    }

    @Override
    protected void onPostExecute(List<JobProfile> s) {
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
