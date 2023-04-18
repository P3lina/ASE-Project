package de.p3lina.domain;

import lombok.Getter;

public class IsWon {

    public IsWon() {
        this.isWon = false;
        this.player = null;
    }
    @Getter
    private boolean isWon;
    @Getter
    private Player player;
    public void setPlayer(Player player) {
        this.player = player;
        this.isWon = true;
    }
}
