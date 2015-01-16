package com.net;

import com.game.entity.player.Player;

public class ActionSender {

    private Player player;

    public ActionSender(Player player) {
        this.player = player;
    }

    public ActionSender sendLobby() {

        return this;
    }
}