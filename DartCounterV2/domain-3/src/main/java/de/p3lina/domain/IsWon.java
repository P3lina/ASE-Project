package de.p3lina.domain;


public class IsWon {

    public IsWon() {
        this.isWon = false;
        this.player = null;
    }
    private boolean isWon;

    public boolean isWon() {
        return isWon;
    }

    public Player getPlayer() {
        return player;
    }

    private Player player;
    public void setPlayer(Player player) {
        this.player = player;
        this.isWon = true;
    }
}
