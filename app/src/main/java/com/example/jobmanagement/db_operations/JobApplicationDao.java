package com.example.jobmanagement.db_operations;

import com.example.jobmanagement.data_models.JobAdvert;
import com.example.jobmanagement.data_models.JobApplication;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

@Dao
public interface JobApplicationDao extends GenericDao<JobApplication> {

    @Query("SELECT * FROM JobApplicationTable")
    List<JobApplication> getAllJobApplications();

    @Query("SELECT * FROM JobApplicationTable WHERE profileId LIKE :profileId")
    List<JobApplication> getUserApplications(long profileId);

    @Query("DELETE FROM jobapplicationtable WHERE jobId = :jobId AND profileId = :applicantId")  // Unsure
    void delete(long jobId, long applicantId);

    @Query("SELECT * FROM JobApplicationTable WHERE " +
            "(jobId LIKE :jobId AND profileId LIKE :profileId)")
    JobApplication findByJobIdAndUserId(long jobId, long profileId);

    @Query("SELECT * FROM JobAdvertTable WHERE id LIKE :profileId")
    List<JobAdvert> getUserAppliedJobAdverts(long profileId);
}
