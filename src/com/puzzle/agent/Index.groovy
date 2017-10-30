package com.puzzle.agent

class Index {
    int col
    int row

    Index(int col, int row) {
        this.col = col
        this.row = row
    }

    @Override
    public Index clone() {
        return new Index(col, row)
    }

    Index() {

    }
}
