package com.game.entity.player;

public enum GameState {

    UPDATE(0), LOGIN(1), LOBBY(2), GAME(3);

    private int value;

    private GameState(int value) {
        this.value = value;
    }
}
