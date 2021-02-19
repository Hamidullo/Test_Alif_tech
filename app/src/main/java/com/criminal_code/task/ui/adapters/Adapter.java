package com.criminal_code.task.ui.adapters;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.criminal_code.task.R;
import com.criminal_code.task.ui.App;
import com.criminal_code.task.ui.Model.Tasks;
import com.criminal_code.task.ui.view.TaskActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.TaskViewHolder> {

    private SortedList<Tasks> sortedList;

    public Adapter() {

        sortedList = new SortedList<>(Tasks.class, new SortedList.Callback<Tasks>() {
            @Override
            public int compare(Tasks o1, Tasks o2) {
                if (!o2.status && o1.status) {
                    return 1;
                }
                if (o2.status && !o1.status) {
                    return -1;
                }
                return (int) (o1.startTime - o2.startTime);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean areContentsTheSame(Tasks oldItem, Tasks newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Tasks item1, Tasks item2) {
                return item1.uid == item2.uid;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Tasks> tasks) {
        sortedList.replaceAll(tasks);
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView tasktext;
        TextView deadlinetext;
        CheckBox completed;
        View delete;

        Tasks task;

        boolean silentUpdate;

        public TaskViewHolder(@NonNull final View itemView) {
            super(itemView);

            tasktext = itemView.findViewById(R.id.task_text);
            deadlinetext = itemView.findViewById(R.id.deadline_text);
            completed = itemView.findViewById(R.id.completed);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TaskActivity.start((Activity) itemView.getContext(), task);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getTaskDao().delete(task);
                }
            });

            completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (!silentUpdate) {
                        task.status = checked;
                        App.getInstance().getTaskDao().update(task);
                    }
                    updateStrokeOut();
                }
            });

        }

        public void bind(Tasks task) {
            this.task = task;

            tasktext.setText(task.task);
            deadlinetext.setText(task.endTime);
            updateStrokeOut();

            silentUpdate = true;
            completed.setChecked(task.status);
            silentUpdate = false;
        }

        private void updateStrokeOut() {
            if (task.status) {
                tasktext.setPaintFlags(tasktext.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                deadlinetext.setPaintFlags(deadlinetext.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                tasktext.setPaintFlags(tasktext.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                deadlinetext.setPaintFlags(deadlinetext.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}

