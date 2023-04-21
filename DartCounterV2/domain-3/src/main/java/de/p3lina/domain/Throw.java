package de.p3lina.domain;


import java.util.ArrayList;
import java.util.List;

public class Throw {
    private List<Dart> darts;
    private boolean checkedOut;

    public List<Dart> getDarts() {
        return darts;
    }

    public void setDarts(List<Dart> darts) {
        this.darts = darts;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Throw() {
        this.darts = new ArrayList<>();
    }

    public void addDart(Dart dart) {
        this.darts.add(dart);
    }

}