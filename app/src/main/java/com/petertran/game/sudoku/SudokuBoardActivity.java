package com.petertran.game.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.petertran.game.sudoku.model.EntryPoint;
import com.petertran.game.sudoku.model.Game;


public class SudokuBoardActivity extends Activity implements View.OnClickListener {
    private int type;
    private int boardSize;
    private Context context;
    private BoardTextView currentCell;
    CharSequence colors[] = new CharSequence[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    int[] colorss = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    //GridView gridView;

    //private SudokuGame sudoku;
    private Game game;

    private BoardTextView[][] squaresArray;
    //private ArrayAdapter<BoardTextView> squaresList;
    private int[][] originalArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_board);
        context = getApplicationContext();
        //gridView = (GridView)findViewById(R.id.grdBoards);
        type = getIntent().getIntExtra("type", 1);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        boardSize = (width - 60) / 10;
        setOriginalArray(new int[9][9]);
        squaresArray = new BoardTextView[9][9];
        //squaresList = new ArrayAdapter<BoardTextView>(context, R.layout.view_board);
        //sudoku = new SudokuGame();
        //sudoku.Generate(30);
        game = new Game();
        //Sudokur s = new Sudokur();
        GenerateBlankArray();
        switch (type) {
            case 1:
                createEmptySquare();
                break;
            case 2:
                createValueSquare();
                break;
            default:
                break;
        }
        //gridView.setAdapter(squaresList);
        setupBoard();
        //game = new Game();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sudoku_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createEmptySquare() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                BoardTextView square = getBoardTextView(i, j);
                square.setPoint(new EntryPoint(i, j));
                square.setValue(0);
                square.setEditable(true);
                square.setTextColor(getResources().getColor(R.color.green));
                squaresArray[i][j] = square;
                //squaresList.add(square);
            }
        }
    }

    private void createValueSquare() {
//        sudoku.setData(this.getOriginalArray());
//        sudoku.Generate(30);
        setOriginalArray(game.getGame());

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                BoardTextView square = getBoardTextView(i, j);
                square.setPoint(new EntryPoint(i, j));
                square.setValue(originalArray[i][j]);
                if (originalArray[i][j] > 0)
                    square.setEditable(false);
                else {
                    square.setEditable(true);
                    square.setTextColor(getResources().getColor(R.color.green));
                }

                squaresArray[i][j] = square;
                //squaresList.add(square);
            }
        }
    }

    private BoardTextView getBoardTextView(int i, int j) {
        BoardTextView square = new BoardTextView(context);
        square.setHeight(boardSize);
        square.setWidth(boardSize);
        square.setBackgroundResource(getBackground(i, j));
        square.setTextColor(getResources().getColor(R.color.violet));
        square.setTextSize(20);
        square.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        square.setOnClickListener(this);
        return square;
    }

    private int getBackground(int i, int j) {
        int background = 0;
        i++;
        j++;
        switch (i) {
            case 1:
            case 2:
            case 3:
                switch (j) {
                    case 1:
                    case 2:
                    case 3:
                        background = R.drawable.text_background_1;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        background = R.drawable.text_background_2;
                        break;
                    case 7:
                    case 8:
                    case 9:
                        background = R.drawable.text_background_3;
                        break;
                }
                break;
            case 4:
            case 5:
            case 6:
                switch (j) {
                    case 1:
                    case 2:
                    case 3:
                        background = R.drawable.text_background_4;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        background = R.drawable.text_background_5;
                        break;
                    case 7:
                    case 8:
                    case 9:
                        background = R.drawable.text_background_6;
                        break;
                }
                break;
            case 7:
            case 8:
            case 9:
                switch (j) {
                    case 1:
                    case 2:
                    case 3:
                        background = R.drawable.text_background_7;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        background = R.drawable.text_background_8;
                        break;
                    case 7:
                    case 8:
                    case 9:
                        background = R.drawable.text_background_9;
                        break;
                }
                break;
            default:
                break;
        }
        return background;
    }

    private void GenerateBlankArray() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                originalArray[i][j] = 0;
            }
        }
    }

    private void setupBoard() {
        LinearLayout rowIndex1 = (LinearLayout) findViewById(R.id.ll_index_horizontal);
        for (int i = 1; i < 10; i++) {
            TextView textView = new TextView(this);
            textView.setText(Integer.toString(i));
            textView.setGravity(Gravity.CENTER);
            textView.setHeight(boardSize);
            textView.setWidth(boardSize);
            rowIndex1.addView(textView);
        }
        LinearLayout rowIndex2 = (LinearLayout) findViewById(R.id.ll_index_vertical);
        TextView blank = new TextView(this);
        blank.setGravity(Gravity.CENTER);
        blank.setHeight(boardSize);
        blank.setWidth(boardSize);
        rowIndex2.addView(blank);
        for (int i = 1; i < 10; i++) {
            TextView textView = new TextView(this);
            textView.setText(Integer.toString(i));
            textView.setGravity(Gravity.CENTER);
            textView.setHeight(boardSize);
            textView.setWidth(boardSize);
            rowIndex2.addView(textView);
        }
        LinearLayout row1 = (LinearLayout) findViewById(R.id.llRow1);
        for (int i = 0; i < 9; i++) {
            row1.addView(squaresArray[0][i]);
        }
        LinearLayout row2 = (LinearLayout) findViewById(R.id.llRow2);
        for (int i = 0; i < 9; i++) {
            row2.addView(squaresArray[1][i]);
        }
        LinearLayout row3 = (LinearLayout) findViewById(R.id.llRow3);
        for (int i = 0; i < 9; i++) {
            row3.addView(squaresArray[2][i]);
        }
        LinearLayout row4 = (LinearLayout) findViewById(R.id.llRow4);
        for (int i = 0; i < 9; i++) {
            row4.addView(squaresArray[3][i]);
        }
        LinearLayout row5 = (LinearLayout) findViewById(R.id.llRow5);
        for (int i = 0; i < 9; i++) {
            row5.addView(squaresArray[4][i]);
        }
        LinearLayout row6 = (LinearLayout) findViewById(R.id.llRow6);
        for (int i = 0; i < 9; i++) {
            row6.addView(squaresArray[5][i]);
        }
        LinearLayout row7 = (LinearLayout) findViewById(R.id.llRow7);
        for (int i = 0; i < 9; i++) {
            row7.addView(squaresArray[6][i]);
        }
        LinearLayout row8 = (LinearLayout) findViewById(R.id.llRow8);
        for (int i = 0; i < 9; i++) {
            row8.addView(squaresArray[7][i]);
        }
        LinearLayout row9 = (LinearLayout) findViewById(R.id.llRow9);
        for (int i = 0; i < 9; i++) {
            row9.addView(squaresArray[8][i]);
        }
    }

    private void check() {
        boolean result = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (squaresArray[i][j].getValue() != game.getSolution()[i][j]) {
                    result = false;
                    break;
                }
            }
        }

        if (result) {
            Intent intent = new Intent(this, CongratulationsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private int showSelector() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a number");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String cs = colors[which].toString();
                int number = Integer.parseInt(cs.toString());
                changeCell(number);
            }
        });
        builder.show();
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof BoardTextView) {
            BoardTextView cell = (BoardTextView) v;
            currentCell = cell;
            if (cell.isEditable()) {
//                int value = cell.getValue();
//                if (value == 9)
//                    value = 1;
//                else value++;
//                cell.setValue(value);
//                check();
                showSelector();
            }
        }
    }

    public void changeCell(int number) {
        currentCell.setValue(number);
        validateInput();
        if(type == 1) {
            check();
        }
    }

    private void validateInput() {
        boolean result = true;
        int xIndex = currentCell.getPoint().getX();
        int yIndex = currentCell.getPoint().getY();

        for (int i = 0; i < 9; i++) {
            if (squaresArray[xIndex][i].getValue() == currentCell.getValue())
                if (i != yIndex) {
                    result = false;
                    break;
                }
        }
        if (result) {
            for (int j = 0; j < 9; j++) {
                if (squaresArray[j][yIndex].getValue() == currentCell.getValue())
                    if (j != xIndex) {
                        result = false;
                        break;
                    }
            }
        }
        if (result) {
            int xSquare = currentCell.getPoint().getX() / 3;
            int ySquare = currentCell.getPoint().getY() / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int x = xSquare * 3 + i;
                    int y = ySquare * 3 + j;
                    if (squaresArray[i][j].getValue() == currentCell.getValue())
                        if (x != xIndex && y != yIndex) {
                            result = false;
                            break;
                        }
                }
            }
        }

        if (result) {
            currentCell.setTextColor(getResources().getColor(R.color.orange));
        } else {
            currentCell.setTextColor(getResources().getColor(R.color.green));
        }
    }

    public int[][] getOriginalArray() {
        return originalArray;
    }

    public void setOriginalArray(int[][] originalArray) {
        this.originalArray = originalArray;
    }

}
