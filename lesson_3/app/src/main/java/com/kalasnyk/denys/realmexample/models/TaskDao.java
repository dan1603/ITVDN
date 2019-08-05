package com.kalasnyk.denys.realmexample.models;

import java.util.List;

public interface TaskDao
{
    List<TaskEntity> getAllTasks();

    TaskEntity getTaskById(int id);

    void saveTask(TaskEntity task);

    void deleteTask(TaskEntity task);
}

