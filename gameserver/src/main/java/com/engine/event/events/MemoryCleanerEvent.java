package com.engine.event.events;

import com.engine.event.Event;

public class MemoryCleanerEvent extends Event {

    public static final int CLEANUP_CYCLE_TIME = 300000;

    public MemoryCleanerEvent() {
        super(CLEANUP_CYCLE_TIME);
    }

    @Override
    public void execute() {
        System.gc();
        System.runFinalization();
    }

}