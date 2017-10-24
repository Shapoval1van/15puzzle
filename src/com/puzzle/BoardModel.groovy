package com.puzzle

import com.puzzle.agent.Index
import com.puzzle.initializer.InitializerFactory
import com.puzzle.initializer.InitializerType

class BoardModel {
    static def SIZE = 4

    private def boardRepresentation = new Integer[SIZE][SIZE]
    private Integer emptyCellIndex

    BoardModel(){

    }

    def init(InitializerType type){
        def initializer = new InitializerFactory().getInitializer(type)
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                def value = initializer.getNextValue()
                if (value == 0){
                    emptyCellIndex = new Index(i, j)
                }
                boardRepresentation[i][j] = value
            }
        }
    }

    def getBoardRepresentation() {
        return boardRepresentation
    }

    def getEmptyCellIndex() {
        return emptyCellIndex
    }
}
