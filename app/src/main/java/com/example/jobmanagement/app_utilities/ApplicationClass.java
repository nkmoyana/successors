package com.example.jobmanagement.app_utilities;

import android.app.Application;

import com.example.jobmanagement.data_models.JobAdvert;

import java.util.ArrayList;


public class ApplicationClass extends Application
{
    public static ArrayList<JobAdvert> jobAdverts;


    @Override
    public void onCreate() {
        super.onCreate();

        jobAdverts = new ArrayList<JobAdvert>();

    }
}
