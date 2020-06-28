package com.example.jobmanagement.db_operations;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.data_models.JobApplication;
import com.example.jobmanagement.data_models.JobProfile;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {JobAdvert.class, JobApplication.class, JobProfile.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract JobAdvertDao getJobAdvertDao();

    public abstract JobApplicationDao getJobApplicationDao();

    public  abstract JobProfileDao getJobProfileDao();
}
