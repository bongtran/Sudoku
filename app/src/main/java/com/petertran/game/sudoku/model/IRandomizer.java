package com.petertran.game.sudoku.model;

/**
 * Created by Peter on 5/11/15.
 */
public interface IRandomizer {
    int GetInt(int max);
    int GetInt(int min, int max);
}
