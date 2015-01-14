package com.engine.tick;

public abstract class Tick {

	/*
	 * What is the difference between remainingTicks and tickDelay? ============================================================
	 *
	 * tickDelay = the delay in ticks between consecutive executions. e.g. a combat event may run at 3 ticks, which mean each 3*600 ms it is executed
	 *
	 * remainingTicks = the number of ticks until this tickable next runs. e.g. the tick delay will be 1 if it'll be ran in the next cycle.
	 */

    private byte remainingTicks;

    private byte tickDelay;

    private boolean running = true;

    public Tick(int ticks) {
        this.remainingTicks = (byte) ticks;
        this.tickDelay = (byte) ticks;
    }

    public int getTickDelay() {
        return tickDelay;
    }

    public int getRemainingTicks() {
        return remainingTicks;
    }

    public void setDelay(int ticks) {
        if (ticks < 0) {
            throw new IllegalArgumentException("Tick amount must be positive.");
        }
        this.tickDelay = (byte) ticks;
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }

    protected abstract void execute();

    public void cycle() {
        if (remainingTicks-- <= 1) {
            if (isRunning()) {
                execute();
            }
            remainingTicks = tickDelay;
        }
    }

}
