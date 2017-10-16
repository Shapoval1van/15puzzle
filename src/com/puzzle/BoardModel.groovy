package com.puzzle

import com.puzzle.initializer.InitializerFactory
import com.puzzle.initializer.InitializerType

class BoardModel {
    static def SIZE = 4

    private def boardRepresentation = new Integer[SIZE][SIZE]
    private def nullValueIndex = new Integer[2]

    BoardModel(){

    }

    def init(InitializerType type){
        def initializer = new InitializerFactory().getInitializer(type)
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                def value = initializer.getNextValue()
                if (value == 0){
                    nullValueIndex[0] = i
                    nullValueIndex[1] = j
                }
                boardRepresentation[i][j] = value
            }
        }
    }

    def getBoardRepresentation() {
        return boardRepresentation
    }

}
