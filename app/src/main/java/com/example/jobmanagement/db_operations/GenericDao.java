package com.example.jobmanagement.db_operations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface GenericDao<T>
{
    @Insert
    public abstract void insert(T user);

    @Delete
    public abstract void delete(T user);

    @Update
    public abstract void update(T user);
}
