package com.criminal_code.task.ui.Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Tasks implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "task")
    public String task;

    @ColumnInfo(name = "startTime")
    public long startTime;

    @ColumnInfo(name = "endTime")
    public String endTime;

    @ColumnInfo(name = "status")
    public boolean status;

    public Tasks() {
    }

    protected Tasks(Parcel in) {
        uid = in.readInt();
        task = in.readString();
        startTime = in.readLong();
        endTime = in.readString();
        status = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(task);
        dest.writeLong(startTime);
        dest.writeString(endTime);
        dest.writeByte((byte) (status ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tasks> CREATOR = new Creator<Tasks>() {
        @Override
        public Tasks createFromParcel(Parcel in) {
            return new Tasks(in);
        }

        @Override
        public Tasks[] newArray(int size) {
            return new Tasks[size];
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tasks tasks = (Tasks) o;
        return uid == tasks.uid &&
                startTime == tasks.startTime &&
                status == tasks.status &&
                Objects.equals(task, tasks.task) &&
                Objects.equals(endTime, tasks.endTime);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(uid, task, startTime, endTime, status);
    }
}
