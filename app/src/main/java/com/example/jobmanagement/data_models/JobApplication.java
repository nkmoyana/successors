package com.example.jobmanagement.data_models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "JobApplicationTable", primaryKeys = {"jobId","profileId"})
public class JobApplication
{
    @ForeignKey(entity = JobAdvert.class, parentColumns = "id", childColumns = "jobId") //onDelete and onUpdate
    private long jobId;
    @ForeignKey(entity = JobProfile.class, parentColumns = "id", childColumns = "profileId") //onDelete and onUpdate
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
