package com.puzzle.agent

class Index {
    int col
    int row

    Index(int col, int row) {
        this.col = col
        this.row = row
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Index(col, row)
    }

    Index() {

    }
}
