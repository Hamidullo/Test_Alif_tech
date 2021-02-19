package com.criminal_code.task.ui.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.criminal_code.task.R;
import com.criminal_code.task.ui.App;
import com.criminal_code.task.ui.Model.Tasks;

import java.util.Calendar;

public class TaskActivity extends AppCompatActivity {

    private static final String EXTRA_TASK = "TaskActivity.EXTRA_TASK";
    private Tasks task;

    private EditText editText;
    private Button date,time;
    private TextView date_time;

    private String strDate = "",strTime = "";
    private int mYear, mMonth, mDay, mHour, mMinute;

    public static void start(Activity caller, Tasks task){
        Intent intent = new Intent(caller, TaskActivity.class);
        if (task != null){
            intent.putExtra(EXTRA_TASK,task);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle("Tasks");

        editText = findViewById(R.id.text);
        date = findViewById(R.id.btn_date);
        time = findViewById(R.id.btn_time);
        date_time = findViewById(R.id.date_time);

        if (getIntent().hasExtra(EXTRA_TASK)){
            task = getIntent().getParcelableExtra(EXTRA_TASK);
            editText.setText(task.task);
        } else {
            task = new Tasks();
        }

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                strDate = "";

                DatePickerDialog datePickerDialog = new DatePickerDialog(TaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                strDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                date_time.setText(strDate + "-" + strTime);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                strTime = "";
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(TaskActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                strTime = hourOfDay + "-" + minute;
                                date_time.setText(strDate + "-" + strTime);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                if (editText.getText().length() > 0){
                    task.task = editText.getText().toString();
                    task.status = false;
                    task.startTime = System.currentTimeMillis();
                    task.endTime = date_time.getText().toString();
                    if (getIntent().hasExtra(EXTRA_TASK)){
                        App.getInstance().getTaskDao().update(task);
                    } else {
                        App.getInstance().getTaskDao().insert(task);
                    }
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}