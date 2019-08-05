package com.kalasnyk.denys.realmexample.realm;

public interface IRealmHelper {

    void changeTaskDone(final String taskId);

    void changeTaskName(final String taskId, final String name);

    void deleteTask(final String taskId);

    void deleteAllDone();

    void addTask(final String taskName);

}