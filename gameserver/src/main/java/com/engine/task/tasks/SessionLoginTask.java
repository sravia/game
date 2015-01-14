package com.engine.task.tasks;

import com.engine.GameEngine;
import com.engine.World;
import com.engine.task.Task;
import com.game.entity.player.Player;

public class SessionLoginTask implements Task {

    private Player player;

    public SessionLoginTask(Player player) {
        this.player = player;
    }

    @Override
    public void execute(GameEngine context) {
        World.getWorld().register(player);
    }

}
