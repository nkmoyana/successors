package com.example.jobmanagement.db_operations;

import com.example.jobmanagement.data_models.JobProfile;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

@Dao
public interface JobProfileDao extends GenericDao<JobProfile> {
    @Query("DELETE FROM jobprofiletable WHERE id = :id")
    abstract void delete(long id);

    @Query("SELECT * FROM JobProfileTable")
    List<JobProfile> getAllJobProfiles();

    @Query("SELECT * FROM JobProfileTable WHERE id LIKE :id")
    JobProfile getJobProfileById(long id);

    @Query("SELECT * FROM JobProfileTable WHERE email LIKE :email")
    JobProfile getJobProfileByEmail(String email);

}
