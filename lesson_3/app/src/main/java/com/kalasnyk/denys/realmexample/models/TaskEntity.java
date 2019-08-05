package com.kalasnyk.denys.realmexample.models;

import android.util.Log;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class TaskEntity extends RealmObject {
    @Required
    @PrimaryKey
    private String id;
    @Required
    private String name;
    private boolean done;
    private long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        Log.d("TASK", "Done status for task " + this.id + " changed to " + String.valueOf(done));
        this.done = done;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

