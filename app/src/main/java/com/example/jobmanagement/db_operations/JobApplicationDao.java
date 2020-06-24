package com.example.jobmanagement.db_operations;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.data_models.JobApplication;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

@Dao
public interface JobApplicationDao extends GenericDao<JobApplication>
{
    @Query("SELECT * FROM JobApplication")
    abstract List<JobApplication> getAllJobApplications();

    @Query("SELECT * FROM JobApplication WHERE profileId LIKE :profileId")
    abstract List<JobApplication> getUserApplications(long profileId);

    @Delete
    abstract void delete(long jobId, long applicantId);

    @Query("SELECT * FROM JobApplication WHERE " +
            "(jobId LIKE :jobId AND profileId LIKE :profileId)")
    abstract JobApplication findByJobIdAndUserId(long jobId, long profileId);

    @Query("SELECT * FROM JobAdvert WHERE id LIKE :profileId")
    abstract List<JobAdvert> getUserAppliedJobAdverts(long profileId);
}
