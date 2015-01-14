package com.engine.event.events;

import com.engine.World;
import com.engine.event.Event;
import com.game.entity.player.Player;

public class RestoreEvent extends Event {

    public RestoreEvent() {
        super(30000);
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub

        for (Player player : World.getWorld().getPlayers()) {
            /*// Check for dead.
            if (player.isDead() || player.isInLobby())
                continue;
            // Normalize levels
            for (int i = 0; i < Skills.SKILL_COUNT; i++)
                player.getSkills().normalizeLevel(i);

            // Increase Health by 1
            if (player.getHealth() < player.getSkills().getMaxLifePoints())
                player.applyHealthChange(1, true);

            // special energy restore.
            if (player.getSpecialAmount() < 100) {
                player.setSpecialAmount((byte) (player.getSpecialAmount() + 10));
                player.getActionSender().sendConfig(300, player.getSpecialAmount() * 10);
            }*/
        }
    }

}
