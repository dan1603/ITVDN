package com.kalasnyk.denys.realmexample.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.kalasnyk.denys.realmexample.R;
import com.kalasnyk.denys.realmexample.models.TaskEntity;
import com.kalasnyk.denys.realmexample.presentation.activities.TaskListActivity;
import com.kalasnyk.denys.realmexample.realm.RealmHelper;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class TaskAdapter extends RealmBaseAdapter<TaskEntity> implements ListAdapter {

    private TaskListActivity activity;

    private static class ViewHolder {
        TextView taskName;
        CheckBox isTaskDone;
    }

    public TaskAdapter(TaskListActivity activity, OrderedRealmCollection<TaskEntity> data) {
        super(data);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_list_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.taskName = (TextView) convertView.findViewById(R.id.task_item_name);
            viewHolder.isTaskDone = (CheckBox) convertView.findViewById(R.id.task_item_done);
            viewHolder.isTaskDone.setOnClickListener(listener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            TaskEntity task = adapterData.get(position);
            viewHolder.taskName.setText(task.getName());
            viewHolder.isTaskDone.setChecked(task.isDone());
            viewHolder.isTaskDone.setTag(position);
        }

        return convertView;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            if (adapterData != null) {
                TaskEntity task = adapterData.get(position);
                new RealmHelper().changeTaskDone(task.getId());
            }
        }
    };
}
