package com.criminal_code.task.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.criminal_code.task.ui.App;
import com.criminal_code.task.ui.Model.Tasks;

import java.util.List;

public class DashboardViewModel extends ViewModel {

    private LiveData<List<Tasks>> taskLiveData = App.getInstance().getTaskDao().getByStatus(false);

    public LiveData<List<Tasks>> getNoteLiveData() {
        return taskLiveData;
    }
}