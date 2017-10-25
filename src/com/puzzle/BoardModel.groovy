package com.puzzle

import com.puzzle.agent.Index
import com.puzzle.initializer.InitializerFactory
import com.puzzle.initializer.InitializerType

class BoardModel {
    static def SIZE = 4

    private def boardRepresentation = new Integer[SIZE][SIZE]
    private Index emptyCellIndex

    BoardModel(){

    }

    BoardModel(boardRepresentation, Index emptyCellIndex) {
        this.boardRepresentation = boardRepresentation
        this.emptyCellIndex = emptyCellIndex
    }

    def init(InitializerType type){
        def initializer = new InitializerFactory().getInitializer(type)
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                def value = initializer.getNextValue()
                if (value == 0){
                     emptyCellIndex = new Index(j, i)
                }
                boardRepresentation[i][j] = value
            }
        }
    }

    def getBoardRepresentation() {
        boardRepresentation
    }

    def getEmptyCellIndex() {
        emptyCellIndex
    }

    @Override
    BoardModel clone() throws CloneNotSupportedException {
        new BoardModel(deepClone(), emptyCellIndex.clone())
    }

    private def deepClone(){
        def array = new Integer[SIZE][SIZE]
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                array[i][j] = boardRepresentation[i][j]
            }
        }
        array
    }
}
