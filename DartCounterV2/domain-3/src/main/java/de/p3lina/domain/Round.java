package de.p3lina.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
public class Round {
    public Round(int roundNumber) {
        this.playerThrows = new HashMap<>();
        this.roundNumber = roundNumber;
    }

    @Getter
    private Map<Player, Throw> playerThrows;

    public void addPlayerThrow(Player player, Throw dartThrow) {
        this.playerThrows.put(player, dartThrow);
    }
    @Getter
    private int roundNumber;
}
