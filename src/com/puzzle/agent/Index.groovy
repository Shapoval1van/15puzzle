package com.puzzle.agent

protected class Index {
    int x
    int y

    Index(int x, int y) {
        this.x = x
        this.y = y
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Index(x, y)
    }

    Index() {

    }
}
