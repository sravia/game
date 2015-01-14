package com.engine.task.tasks;

import com.engine.GameEngine;
import com.engine.task.Task;
import com.game.entity.Entity;

public class ResetTask implements Task {

    private Entity r;

    public ResetTask(Entity o) {
        this.r = o;
    }

    @Override
    public void execute(GameEngine context) {
        /*r.getUpdateFlags().reset();
        r.setTeleporting(false);
        r.setMapRegionChanging(false);
        if (r instanceof Player) {
            Player player = (Player) r;
            player.resetTeleportTarget();
            player.resetCachedUpdateBlock();
            if (player.getViewDistance() < 15)
                player.increaseViewDist();
        }
        r.getWalkingQueue().setRegionDirectionChange(null);
        r.getDamage().getSplats().clear();
        r.reset();*/
    }

}