package com.example.jobmanagement.data_models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import io.reactivex.annotations.NonNull;

@Entity(indices = {@Index(value = {"email", "identityNumber"},
        unique = true)})
public class JobProfile
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    @NonNull
    private long id;


    @ColumnInfo
    private String email;

    @ColumnInfo
    private String password;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String surname;

    @ColumnInfo
    private String cellphone;

    @ColumnInfo
    private String identityNumber;

    @ColumnInfo
    private String qualification;

    @ColumnInfo
    private String education;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
