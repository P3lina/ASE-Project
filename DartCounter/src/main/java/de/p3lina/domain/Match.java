package de.p3lina.domain;

import lombok.*;

import java.util.List;
@RequiredArgsConstructor
public class Match {

    @Getter
    @Setter
    private List<Set> sets;
    @NonNull
    private int legCount;
    @NonNull
    private int setCount;
    @NonNull
    private List<Player> players;
    @NonNull
    private int startScore;
}
