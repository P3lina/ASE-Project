package de.p3lina.application;

import de.p3lina.domain.Player;

public class PlayerMock extends Player {

    public PlayerMock(String name) {
        super(name);
    }
    @Override
    public String getName() {
        return "Player 1";
    }
}
