package com.criminal_code.task.ui.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.criminal_code.task.ui.Model.Tasks;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Tasks")
    List<Tasks> getAll();

    @Query("SELECT * FROM Tasks")
    LiveData<List<Tasks>> getAllLiveData();

    @Query("SELECT * FROM Tasks WHERE uid IN (:taskIds)")
    List<Tasks> loadAllByIds(int[] taskIds);

    @Query("SELECT * FROM Tasks WHERE status = :status")
    LiveData<List<Tasks>> getByStatus(boolean status);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tasks task);

    @Update
    void update(Tasks task);

    @Delete
    void delete(Tasks task);


}
