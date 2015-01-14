package com.engine.task;

import com.engine.GameEngine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Executes in an order
 */
public class ConsecutiveTask implements Task {

    private Collection<Task> tasks;

    public ConsecutiveTask(Task... tasks) {
        List<Task> taskList = new ArrayList<Task>();
        for (Task task : tasks) {
            taskList.add(task);
        }
        this.tasks = Collections.unmodifiableCollection(taskList);
    }

    @Override
    public void execute(GameEngine context) {
        for (Task task : tasks) {
            task.execute(context);
        }
    }

}
