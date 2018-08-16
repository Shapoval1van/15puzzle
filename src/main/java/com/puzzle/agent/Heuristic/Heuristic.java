package com.puzzle.agent.Heuristic;

import com.puzzle.algorithm.State;

public interface Heuristic {
    final Integer[][] TERMINATE_STATE = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

    Integer getH(State state);
}
