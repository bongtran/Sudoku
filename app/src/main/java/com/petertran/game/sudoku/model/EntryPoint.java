package com.petertran.game.sudoku.model;

/**
 * Created by Peter on 5/11/15.
 */
public class EntryPoint {
    private int x;
    private int y;
    public EntryPoint(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
