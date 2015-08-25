package com.petertran.game.sudoku.model;

/**
 * Created by Peter on 5/11/15.
 */
public class Tuple<X, Y> {
    public final X x;
    public final Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}