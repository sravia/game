package com.engine.task.tasks;

import com.engine.GameEngine;
import com.engine.task.Task;
import com.game.entity.player.Player;

import java.util.Iterator;

/**
 * This is used to spawn local player for every player within a specific distance.
 * Players see only other players if we send it from this task.
 */
public class PlayerUpdateTask implements Task {

    private Player player;

    public PlayerUpdateTask(Player player) {
        this.player = player;
    }

    @Override
    public void execute(GameEngine context) {

    }


}
