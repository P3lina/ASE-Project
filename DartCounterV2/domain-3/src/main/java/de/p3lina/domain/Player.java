package de.p3lina.domain;

import lombok.Getter;
import lombok.Setter;

public class Player {

    @Getter
    private String name;

    public Player(String name){
        this.name = name;
    }


}
