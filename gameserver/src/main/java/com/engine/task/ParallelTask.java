package com.engine.task;

import com.engine.GameEngine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParallelTask implements Task {

    private Collection<Task> tasks;

    public ParallelTask(Task... tasks) {
        List<Task> taskList = new ArrayList<Task>();
        for (Task task : tasks) {
            taskList.add(task);
        }
        this.tasks = Collections.unmodifiableCollection(taskList);
    }

    @Override
    public void execute(final GameEngine context) {
        for (final Task task : tasks) {
            context.submitTask(new Runnable() {
                @Override
                public void run() {
                    task.execute(context);
                }
            });
        }
        try {
            context.waitForPendingParallelTasks();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
