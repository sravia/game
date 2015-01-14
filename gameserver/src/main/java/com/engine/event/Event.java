package com.engine.event;

import com.engine.task.tasks.ResetTask;

public abstract class Event {

    private long delay;
    private boolean running = true;

    public Event(long delay) {
        this.delay = delay;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        if (delay < 0) {
            throw new IllegalArgumentException("Delay must be positive.");
        }
        this.delay = delay;
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }

    public abstract void execute();

}
