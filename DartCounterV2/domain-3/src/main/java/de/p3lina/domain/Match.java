package de.p3lina.domain;

import lombok.*;

import java.util.List;
@RequiredArgsConstructor
public class Match {

    @Getter
    @Setter
    @NonNull
    private List<Set> sets;
    @NonNull
    private int legCount;
    @NonNull
    private int setCount;
    @NonNull
    @Getter
    private List<Player> players;
    @NonNull
    private int startScore;
    @Getter
    @Setter
    private Player winner;
}
