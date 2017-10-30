package com.puzzle.agent.Heuristic;

import com.puzzle.BoardModel;
import com.puzzle.algorithm.State;

import static java.lang.Math.abs;

public class ManhattanAndLinearHeuristic implements Heuristic {
    @Override
    public Integer getH(State state) {
        return findManhattan(state);
    }

    public Integer findManhattan(State state) {
        Integer[][] representation = state.getBoardModel().getBoardRepresentation();
        Integer result = 0;
        for (int i = 0; i < BoardModel.SIZE; i++) {
            for (int j = 0; j < BoardModel.SIZE; j++) {
                if (representation[i][j] != TERMINATE_STATE[i][j]) {
                    result += calculateManhattanToCell(representation[i][j], j, i);
                }
            }
        }
        return result;
    }

    public Integer calculateManhattanToCell(int cellValue, int currColl, int currRow) {
        int termStateCellCol;
        int termStateCellRow;
        if (cellValue == 0) {
            termStateCellRow = 3;
            termStateCellCol = 3;
        }else {
            termStateCellCol = (cellValue - 1) % BoardModel.SIZE;
            termStateCellRow = (cellValue - 1) / BoardModel.SIZE;
        }
        return abs(currRow - termStateCellRow) + abs(currColl - termStateCellCol);
    }
}
