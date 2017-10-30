package com.puzzle.agent;

public class Index {
    private int col;
    private int row;

    public Index(int col, int row) {
        this.col = col;
        this.row = row;
    }

    @Override
    public Index clone() {
        return new Index(col, row);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
