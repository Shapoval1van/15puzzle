package com.puzzle.initializer.impl;

import com.puzzle.BoardModel;
import com.puzzle.agent.Agent;
import com.puzzle.agent.Direct;
import com.puzzle.agent.DirectNotAllowedException;
import com.puzzle.agent.Index;
import com.puzzle.algorithm.State;
import com.puzzle.initializer.Initializer;

import java.util.List;
import java.util.Random;

public class ShiftInitializer implements Initializer{
    private final Integer[][] TERMINAL_STATE = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    private final int SHIFT_COUNT = 150;

    public Integer[][] mixTerminalState() {
        Agent agent = new Agent();
        Random random = new Random();
        State state = new State(new BoardModel(TERMINAL_STATE, new Index(3, 3)));
        for(int i = 0; i < SHIFT_COUNT; i++) {
            List<Direct> allowedDirectList = agent.getAllowedDirectList(state);
            try {
                state = agent.move(allowedDirectList.get(random.nextInt(allowedDirectList.size())), state);
            } catch (DirectNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        }
        return state.getBoardModel().getBoardRepresentation();
    }

    @Override
    public Integer[][] getInitialState() {
        return mixTerminalState();
    }
}
