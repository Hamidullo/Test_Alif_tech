package com.criminal_code.task.ui.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.criminal_code.task.ui.Model.Tasks;

@Database(entities = {Tasks.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
