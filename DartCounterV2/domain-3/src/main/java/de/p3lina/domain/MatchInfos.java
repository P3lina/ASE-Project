package de.p3lina.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MatchInfos {
    @NonNull
    @Getter
    private int playerCount;
    @NonNull
    @Getter
    private List<Player> players;
    @NonNull
    @Getter
    private int startScore;
    @NonNull
    @Getter
    private int setCount;
    @NonNull
    @Getter
    private int legCount;
}