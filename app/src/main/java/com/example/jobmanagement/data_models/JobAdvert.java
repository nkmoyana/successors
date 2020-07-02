package com.example.jobmanagement.data_models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "JobAdvertTable")
public class JobAdvert implements Parcelable //I implemented this interface to pass object between activities
{// I will explain everything I did by use of comments
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String jobTitle;
    private String jobSalary;
    private String jobLocation;
    private String appointmentType;
    private String jobPosition;
    private String jobCompany;
    private String jobDescription;
    private boolean isLicence;
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

    public JobAdvert()
    {

    }

    //This constructor is used for parcel

    protected JobAdvert(Parcel in) {

        jobTitle = in.readString();
        jobSalary = in.readString();
        jobLocation = in.readString();
        appointmentType = in.readString();
        jobPosition = in.readString();
        jobCompany = in.readString();
        jobDescription = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isLicence = in.readBoolean();
        }
        jobQualification = in.readString();
    }

    public JobAdvert(String jobTitle, String jobSalary, String jobLocation,
                     String appointmentType, String jobPosition, String jobCompany,
                     String jobDescription, Boolean isLicence, String jobQualification)
    {
        this.jobTitle = jobTitle;
        this.jobSalary = jobSalary;
        this.jobLocation = jobLocation;
        this.appointmentType = appointmentType;
        this.jobPosition = jobPosition;
        this.jobCompany = jobCompany;
        this.jobDescription = jobDescription;
        this.isLicence = isLicence;
        this.jobQualification = jobQualification;
    }

    //This method binds everything together
    public static final Creator<JobAdvert> CREATOR = new Creator<JobAdvert>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public JobAdvert createFromParcel(Parcel in) {
            return new JobAdvert(in);
        }

        @Override
        public JobAdvert[] newArray(int size) {
            return new JobAdvert[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public static Creator<JobAdvert> getCREATOR() {
        return CREATOR;
    }

    //In this method we write class properties to the parcel which we are needed for transfer
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(jobTitle);
        parcel.writeString(jobSalary);
        parcel.writeString(jobLocation);
        parcel.writeString(appointmentType);
        parcel.writeString(jobCompany);
        parcel.writeString(jobCompany);
        parcel.writeString(jobDescription);
        parcel.writeString(String.valueOf(isLicence));
        parcel.writeString(jobQualification);
    }
}
