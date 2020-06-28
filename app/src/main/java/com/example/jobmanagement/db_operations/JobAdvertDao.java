package com.example.jobmanagement.db_operations;

import com.example.jobmanagement.data_models.JobAdvert;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

@Dao
public interface JobAdvertDao extends GenericDao<JobAdvert>
{
    @Delete
    abstract void delete(long id);

    @Query("SELECT * FROM JobAdvert")
    abstract List<JobAdvert> getAllJobAdverts();

    @Query("SELECT * FROM JobAdvert WHERE id LIKE :id")
    abstract JobAdvert getJobAdvertById(long id);

}

