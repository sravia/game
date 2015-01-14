package com.engine.tick;

import java.util.LinkedList;
import java.util.List;

public class TickManager {

    private List<Tick> tickables = new LinkedList<Tick>();

    public List<Tick> getTickables() {
        return tickables;
    }

    public void submit(final Tick tickable) {
        tickables.add(tickable);
    }

}
