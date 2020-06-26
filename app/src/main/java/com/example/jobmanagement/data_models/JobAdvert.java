package com.example.jobmanagement.data_models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class JobAdvert
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private long id;

    @ColumnInfo
    private String jobTitle;

    @ColumnInfo
    private String jobSalary;

    @ColumnInfo
    private String jobLocation;

    @ColumnInfo
    private String appointmentType;

    @ColumnInfo
    private String jobPosition;

    @ColumnInfo
    private String jobCompany;

    @ColumnInfo
    private String jobDescription;

    @ColumnInfo
    private boolean isLicence;

    @ColumnInfo
    private String jobQualification;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public boolean isLicence() {
        return isLicence;
    }

    public void setLicence(boolean licence) {
        isLicence = licence;
    }

    public String getJobQualification() {
        return jobQualification;
    }

    public void setJobQualification(String jobQualification) {
        this.jobQualification = jobQualification;
    }
}
