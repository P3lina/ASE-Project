package de.p3lina.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class Match {

    public Match(MatchInfos matchInfos) {
        this.players = matchInfos.getPlayers();
        this.startScore = matchInfos.getStartScore();
        this.setCount = matchInfos.getSetCount();
        this.legCount = matchInfos.getLegCount();
        this.sets = new ArrayList<>();
    }

    @Getter
    @NonNull
    private List<Set> sets;
    private int legCount;
    private int setCount;
    @NonNull
    @Getter
    private List<Player> players;
    @NonNull
    private int startScore;
    @Getter
    @Setter
    private Player winner;

    public void addSet(Set set) {
        this.sets.add(set);
    }

}
