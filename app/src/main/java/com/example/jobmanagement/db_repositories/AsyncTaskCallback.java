package com.example.jobmanagement.db_repositories;

public interface AsyncTaskCallback<T> {

     void onSuccess(T success);
     void onException(Exception e);
}
