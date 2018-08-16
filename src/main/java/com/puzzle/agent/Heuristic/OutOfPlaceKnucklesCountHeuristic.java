package com.puzzle.agent.Heuristic;

import com.puzzle.BoardModel;
import com.puzzle.algorithm.State;

public class OutOfPlaceKnucklesCountHeuristic implements Heuristic {

    public Integer getH(State currState) {
        Integer[][] boardRepresentation = currState.getBoardModel().getBoardRepresentation();
        int result = 0;
        for (int i = 0; i < BoardModel.SIZE; i++) {
            for (int j = 0; j < BoardModel.SIZE; j++) {
                if (boardRepresentation[i][j] != TERMINATE_STATE[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }
}
