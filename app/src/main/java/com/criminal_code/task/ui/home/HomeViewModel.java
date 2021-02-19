package com.criminal_code.task.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.criminal_code.task.ui.App;
import com.criminal_code.task.ui.Model.Tasks;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private LiveData<List<Tasks>> taskLiveData = App.getInstance().getTaskDao().getAllLiveData();

    public LiveData<List<Tasks>> getNoteLiveData() {
        return taskLiveData;
    }
}