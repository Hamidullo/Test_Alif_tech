package com.criminal_code.task.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.criminal_code.task.ui.App;
import com.criminal_code.task.ui.Model.Tasks;

import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private LiveData<List<Tasks>> taskLiveData = App.getInstance().getTaskDao().getByStatus(true);

    public LiveData<List<Tasks>> getNoteLiveData() {
        return taskLiveData;
    }
}