package com.example.jobmanagement.db_operations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface GenericDao<T>
{
    @Insert
    void insert(T user);

    @Delete
    void delete(T user);

    @Update
    void update(T user);
}
