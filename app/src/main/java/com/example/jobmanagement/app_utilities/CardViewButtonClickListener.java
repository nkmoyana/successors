package com.example.jobmanagement.app_utilities;

import android.view.View;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.data_models.JobApplication;


public interface CardViewButtonClickListener
{
    abstract void onEditAdvertClick(JobAdvert jobAdvert);

    abstract void onViewAdvertClick(int index);

    abstract void onDeleteAdvertClick(JobAdvert jobAdvert);

    abstract void onJobApplicationClick(JobApplication jobApplication);
}
