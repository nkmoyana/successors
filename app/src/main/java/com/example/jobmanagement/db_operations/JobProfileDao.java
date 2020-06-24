package com.example.jobmanagement.db_operations;

import com.example.jobmanagement.data_models.JobProfile;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

@Dao
public interface JobProfileDao extends GenericDao<JobProfile>
{
    @Delete
    abstract void delete(long id);

    @Query("SELECT * FROM JobProfile")
    abstract List<JobProfile> getAllJobProfiles();

    @Query("SELECT * FROM JobProfile WHERE id LIKE :id")
    abstract JobProfile getJobProfileById(long id);

    @Query("SELECT * FROM JobProfile WHERE email LIKE :email")
    abstract JobProfile getJobProfileByEmail(String email);

}
