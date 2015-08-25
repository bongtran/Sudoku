package com.petertran.game.sudoku;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.petertran.game.sudoku.model.EntryPoint;

/**
 * Created by Peter on 5/14/15.
 */
public class BoardTextView extends TextView {
    private EntryPoint point;
    private boolean editable;
    private int value;

    public BoardTextView(Context context) {
        super(context);
    }

    public BoardTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoardTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EntryPoint getPoint() {
        return point;
    }

    public void setPoint(EntryPoint point) {
        this.point = point;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if (value != 0) {
            this.setText(Integer.toString(value));
        } else {
        }
    }

//    public BoardTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
}
