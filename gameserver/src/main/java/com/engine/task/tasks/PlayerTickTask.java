package com.engine.task.tasks;

import com.engine.GameEngine;
import com.engine.task.Task;
import com.game.entity.player.Player;

public class PlayerTickTask implements Task {

    private Player player;

    public PlayerTickTask(Player player) {
        this.player = player;
    }

    @Override
    public void execute(GameEngine context) {

        System.out.println("update something for player");
    }

}
