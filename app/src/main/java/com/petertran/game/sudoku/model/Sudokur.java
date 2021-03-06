package com.petertran.game.sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Peter on 5/16/15.
 */
public class Sudokur {
    private int[][] solution;       // Generated solution.
    private int[][] game;           // Generated game with user input.
    private boolean[][] check;      // Holder for checking validity of game.
    private int selectedNumber;     // Selected number by user.
    private boolean help;
             // Help turned on or off.
    public Sudokur() {
        newGame();
        setCheck(new boolean[9][9]);
        setHelp(true);
    }

    /**
     * Generates a new Sudoku game.<br />
     * All observers will be notified, update action: new game.
     */
    public void newGame() {
        setSolution(generateSolution(new int[9][9], 0));
        setGame(generateGame(copy(getSolution())));
    }

    private int[][] generateSolution(int[][] game, int index) {
        if (index > 80)
            return game;
        int x = index % 9;
        int y = index / 9;
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++){
            numbers.add(i);}
        Collections.shuffle(numbers);
        while (numbers.size() > 0) {
            int number = getNextPossibleNumber(game, x, y, numbers);
            if (number == -1)
                return null;
            game[y][x] = number;
            int[][] tmpGame = generateSolution(game, index + 1);
            if (tmpGame != null)
                return tmpGame;
            game[y][x] = 0;
        }
        return null;
    }

    private int getNextPossibleNumber(int[][] game, int x, int y, List<Integer> numbers) { while (numbers.size() > 0) { int number = numbers.remove(0); if (isPossibleX(game, y, number) && isPossibleY(game, x, number) && isPossibleBlock(game, x, y, number)) return number; } return -1; }

    private int[][] generateGame(int[][] game) { List<Integer> positions = new ArrayList<Integer>(); for (int i = 0; i < 81; i++) positions.add(i); Collections.shuffle(positions); return generateGame(game, positions); }

    private int[][] generateGame(int[][] game, List<Integer> positions) { while (positions.size() > 0) { int position = positions.remove(0); int x = position % 9; int y = position / 9; int temp = game[y][x]; game[y][x] = 0; if (!isValid(game)) game[y][x] = temp; } return game; }

    private boolean isValid(int[][] game) { return isValid(game, 0, new int[]{0}); }

    private boolean isValid(int[][] game, int index, int[] numberOfSolutions) { if (index > 80) return ++numberOfSolutions[0] == 1; int x = index % 9; int y = index / 9; if (game[y][x] == 0) { List<Integer> numbers = new ArrayList<Integer>(); for (int i = 1; i <= 9; i++) numbers.add(i); while (numbers.size() > 0) { int number = getNextPossibleNumber(game, x, y, numbers); if (number == -1) break; game[y][x] = number; if (!isValid(game, index + 1, numberOfSolutions)) { game[y][x] = 0; return false; } game[y][x] = 0; } } else if (!isValid(game, index + 1, numberOfSolutions)) return false; return true; }

    public void checkGame() {
        setSelectedNumber(0);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++)
                getCheck()[y][x] = getGame()[y][x] == getSolution()[y][x];
        }
    }

    private int[][] copy(int[][] game) {
        int[][] copy = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++)
                copy[y][x] = game[y][x];
        }
        return copy;
    }

    private boolean isPossibleX(int[][] game, int y, int number) {
        for (int x = 0; x < 9; x++) {
            if (game[y][x] == number)
                return false;
        }
        return true;
    }

    /**
     * Returns whether given number is candidate on y axis for given game.
     *
     * @param game      Game to check.
     * @param x         Position of y axis to check.
     * @param number    Number to check.
     * @return          True if number is candidate on y axis, false otherwise.
     */
    private boolean isPossibleY(int[][] game, int x, int number) {
        for (int y = 0; y < 9; y++) {
            if (game[y][x] == number)
                return false;
        }
        return true;
    }

    private boolean isPossibleBlock(int[][] game, int x, int y, int number) {
        int x1 = x < 3 ? 0 : x < 6 ? 3 : 6;
        int y1 = y < 3 ? 0 : y < 6 ? 3 : 6;
        for (int yy = y1; yy < y1 + 3; yy++) {
            for (int xx = x1; xx < x1 + 3; xx++) {
                if (game[yy][xx] == number)
                    return false;
            }
        }
        return true;
    }


    public int[][] getSolution() {
        return solution;
    }

    public void setSolution(int[][] solution) {
        this.solution = solution;
    }

    public int[][] getGame() {
        return game;
    }

    public void setGame(int[][] game) {
        this.game = game;
    }

    public boolean[][] getCheck() {
        return check;
    }

    public void setCheck(boolean[][] check) {
        this.check = check;
    }

    public int getSelectedNumber() {
        return selectedNumber;
    }

    public void setSelectedNumber(int selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }
}
