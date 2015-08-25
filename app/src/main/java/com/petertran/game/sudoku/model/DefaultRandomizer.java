package com.petertran.game.sudoku.model;

import java.util.Random;

/**
 * Created by Peter on 5/11/15.
 */
public class DefaultRandomizer implements IRandomizer{

    public int GetInt(int max)
    {
        Random rnd = new Random();

        return rnd.nextInt(max);
    }

    public int GetInt(int min, int max) {
        return nextInt(min, max);
    }

    public static int nextInt(int min, int max) {
        Random random = new Random();
        long range = (long)max - (long)min + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * random.nextDouble());
        int randomNumber =  (int)(fraction + min);
        return randomNumber;
    }
}
