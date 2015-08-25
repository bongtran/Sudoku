package model;

import com.petertran.game.sudoku.model.SudokuGame;

import junit.framework.TestCase;

/**
 * Created by Peter on 5/29/15.
 */
public class SudokuTest extends TestCase{

    public void testGenerate() throws Exception {
        SudokuGame sudoku = new SudokuGame();
        sudoku.setData(new int[9][9]);
        sudoku.Generate(30);
    }
}