package de.p3lina.domain;

import lombok.Getter;

public class Player {

    @Getter
    String name;

    public Player(String name){
        this.name = name;
    }

}
