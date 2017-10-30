package com.puzzle;

import com.puzzle.agent.Index;
import com.puzzle.initializer.Initializer;
import com.puzzle.initializer.InitializerFactory;
import com.puzzle.initializer.InitializerType;

public class BoardModel {
    public static int SIZE = 4;

    private Integer[][] boardRepresentation = new Integer[SIZE][SIZE];
    private Index emptyCellIndex;

    BoardModel(){

    }

    BoardModel(Integer[][] boardRepresentation, Index emptyCellIndex) {
        this.boardRepresentation = boardRepresentation;
        this.emptyCellIndex = emptyCellIndex;
    }

    void init(InitializerType type){
        Initializer initializer = new InitializerFactory().getInitializer(type);
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                Integer value = (Integer) initializer.getNextValue();
                if (value == 0){
                     emptyCellIndex = new Index(j, i);
                }
                boardRepresentation[i][j] = value;
            }
        }
    }

    public Integer[][] getBoardRepresentation() {
        return boardRepresentation;
    }

    public Index getEmptyCellIndex() {
        return emptyCellIndex;
    }

    @Override
    public BoardModel clone() {
        return new BoardModel(deepClone(), emptyCellIndex.clone());
    }

    private Integer[][] deepClone(){
        Integer[][] array = new Integer[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                array[i][j] = boardRepresentation[i][j];
            }
        }
        return array;
    }
}
