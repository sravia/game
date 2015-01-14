package com.engine.event.events;

import com.engine.World;
import com.engine.event.Event;
import com.engine.task.ConsecutiveTask;
import com.engine.task.ParallelTask;
import com.engine.task.Task;
import com.engine.tick.Tick;
import com.game.entity.player.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityUpdateEvent extends Event {

    public static final int CYCLE_TIME = 600;

    public EntityUpdateEvent() {
        super(CYCLE_TIME);
    }

    @Override
    public void execute() {
        if (World.getWorld().getPlayers().size() == 0)
            return;

        Iterator<Tick> tickIt$ = World.getWorld().getTickManager().getTickables().iterator();
        while (tickIt$.hasNext()) {
            Tick t = tickIt$.next();
            t.cycle();
            if (!t.isRunning()) {
                tickIt$.remove();
            }
        }

        List<Task> tickTasks = new ArrayList<Task>();
        List<Task> updateTasks = new ArrayList<Task>();
        List<Task> resetTasks = new ArrayList<Task>();

        Iterator<Player> it$ = World.getWorld().getPlayers().iterator();
        while (it$.hasNext()) {
            Player player = it$.next();
            tickTasks.add(player.getTickTask());
            updateTasks.add(player.getUpdateTask());
        }

        Task tickTask = new ConsecutiveTask(tickTasks.toArray(new Task[0]));
        Task updateTask = new ParallelTask(updateTasks.toArray(new Task[0]));
        Task resetTask = new ParallelTask(resetTasks.toArray(new Task[0]));

        World.getWorld().submit(new ConsecutiveTask(tickTask, updateTask, resetTask));
    }

}
