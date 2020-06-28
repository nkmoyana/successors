package com.example.jobmanagement.db_operations;

import com.example.jobmanagement.data_models.JobAdvert;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

@Dao
public interface JobAdvertDao extends GenericDao<JobAdvert> {
    @Query("DELETE FROM JobAdvertTable WHERE id = :id")
    abstract void delete(long id);

    @Query("SELECT * FROM JobAdvertTable")
    List<JobAdvert> getAllJobAdverts();

    @Query("SELECT * FROM JobAdvertTable WHERE id LIKE :id")
    JobAdvert getJobAdvertById(long id);

}

