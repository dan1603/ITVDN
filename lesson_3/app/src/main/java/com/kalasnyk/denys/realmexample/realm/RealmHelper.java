package com.kalasnyk.denys.realmexample.realm;

import android.util.Log;

import com.kalasnyk.denys.realmexample.models.TaskEntity;

import java.util.UUID;

import io.realm.Realm;

public class RealmHelper implements IRealmHelper {

    private Realm realm = Realm.getDefaultInstance();

    @Override
    public void changeTaskDone(final String taskId) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TaskEntity task = realm.where(TaskEntity.class).equalTo("id", taskId).findFirst();
                task.setDone(!task.isDone());
            }
        });
    }

    @Override
    public void changeTaskName(final String taskId, final String name) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TaskEntity task = realm.where(TaskEntity.class).equalTo("id", taskId).findFirst();
                Log.d("TASK", "Task name for id  " + taskId + " changed to " + name);
                task.setName(name);
            }
        });
    }

    @Override
    public void deleteTask(final String taskId) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(TaskEntity.class).equalTo("id", taskId)
                        .findFirst()
                        .deleteFromRealm();
                Log.d("TASK", "Task with id " + taskId + " deleted");
            }
        });
    }

    @Override
    public void deleteAllDone() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(TaskEntity.class).equalTo("done", true)
                        .findAll()
                        .deleteAllFromRealm();
                Log.d("TASK", "All task that were marked as done deleted");
            }
        });
    }

    @Override
    public void addTask(final String taskName) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UUID uuid = UUID.randomUUID();
                TaskEntity task = realm.createObject(TaskEntity.class, uuid.toString());
                task.setName(taskName);
                task.setTimestamp(System.currentTimeMillis());
                Log.d("TASK", "Added new task with id " + uuid.toString() + " name " + taskName);
            }
        });
    }
}