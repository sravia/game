package com.engine.event;

import com.engine.GameEngine;

import java.util.concurrent.TimeUnit;

public class EventManager {

    private GameEngine engine;

    public EventManager(GameEngine engine) {
        this.engine = engine;
    }

    public void submit(final Event event) {
        submit(event, event.getDelay());
    }

    private void submit(final Event event, final long delay) {
        engine.scheduleLogic(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                if (event.isRunning()) {
                    event.execute();
                }
                long elapsed = System.currentTimeMillis() - start;
                long remaining = event.getDelay() - elapsed;
                if (remaining <= 0) {
                    remaining = 0;
                }
                submit(event, remaining);
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

}
