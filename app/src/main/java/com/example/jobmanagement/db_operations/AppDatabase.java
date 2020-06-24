package com.example.jobmanagement.db_operations;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.data_models.JobApplication;
import com.example.jobmanagement.data_models.JobProfile;

import androidx.room.Database;

@Database(entities = {JobAdvert.class, JobApplication.class, JobProfile.class},
        version = 1)
public class AppDatabase {
}
