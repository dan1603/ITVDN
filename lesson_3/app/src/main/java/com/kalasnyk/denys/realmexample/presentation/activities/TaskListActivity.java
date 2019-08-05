package com.kalasnyk.denys.realmexample.presentation.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.kalasnyk.denys.realmexample.R;
import com.kalasnyk.denys.realmexample.presentation.adapters.TaskAdapter;
import com.kalasnyk.denys.realmexample.models.TaskEntity;
import com.kalasnyk.denys.realmexample.realm.RealmHelper;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class TaskListActivity extends AppCompatActivity {

    private Realm realm;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper();

        RealmResults<TaskEntity> tasks = realm.where(TaskEntity.class).findAll();
        tasks = tasks.sort("timestamp");
        final TaskAdapter adapter = new TaskAdapter(this, tasks);

        ListView listView = (ListView) findViewById(R.id.task_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final TaskEntity task = (TaskEntity) adapterView.getAdapter().getItem(i);
                final EditText taskEditText = new EditText(TaskListActivity.this);
                taskEditText.setText(task.getName());
                AlertDialog dialog = new AlertDialog.Builder(TaskListActivity.this)
                        .setTitle("Edit TaskEntity")
                        .setView(taskEditText)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                realmHelper.changeTaskName(task.getId(), String.valueOf(taskEditText.getText()));
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                realmHelper.deleteTask(task.getId());
                            }
                        })
                        .create();
                dialog.show();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText taskEditText = new EditText(TaskListActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(TaskListActivity.this)
                        .setTitle("Add TaskEntity")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                realmHelper.addTask(String.valueOf(taskEditText.getText()));
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_delete) {
            realmHelper.deleteAllDone();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
