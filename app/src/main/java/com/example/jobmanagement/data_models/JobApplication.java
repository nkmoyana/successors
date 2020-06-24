package com.example.jobmanagement.data_models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class JobApplication
{
    @PrimaryKey
    @ColumnInfo
    @ForeignKey(entity = JobAdvert.class, parentColumns = "id", childColumns = "jobId")
    private long jobId;

    @ColumnInfo
    @ForeignKey(entity = JobProfile.class, parentColumns = "id", childColumns = "profileId")
    private long profileId;

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }
}
