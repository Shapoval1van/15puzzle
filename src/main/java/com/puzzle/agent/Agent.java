package com.puzzle.agent;

import com.puzzle.BoardModel;
import com.puzzle.algorithm.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.puzzle.agent.Direct.LEFT;
import static com.puzzle.agent.Direct.RIGHT;
import static com.puzzle.agent.Direct.DOWN;
import static com.puzzle.agent.Direct.UP;

public class Agent {
    private final Integer[][] TERMINAL_STATE = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

    public State move(Direct direct, State state) throws DirectNotAllowedException {
        if (getDirectBlockedList(state).contains(direct)) {
            throw new DirectNotAllowedException("Direct: " +direct+" not allowed");
        }
        return swapEmptyCell(direct, state);
    }

    private List<Direct> getDirectBlockedList(State state) {
        BoardModel boardModel = state.getBoardModel();
        List<Direct> blockedDirectsList = new ArrayList<>();
        Index emptyCellIndex = boardModel.getEmptyCellIndex();
        if (emptyCellIndex.getCol() == 0)
            blockedDirectsList.add(LEFT);
        else if (emptyCellIndex.getCol() == (BoardModel.SIZE - 1))
            blockedDirectsList.add(RIGHT);
        if (emptyCellIndex.getRow() == 0)
            blockedDirectsList.add(DOWN);
        else if (emptyCellIndex.getRow() == (BoardModel.SIZE - 1))
            blockedDirectsList.add(UP);
        return blockedDirectsList;
    }

    private void recalculateEmptyCellIndex(Direct direct, State state) {
        BoardModel boardModel = state.getBoardModel();
        Index emptyCellIndex = boardModel.getEmptyCellIndex();
        switch (direct) {
            case UP:
                emptyCellIndex.setRow(emptyCellIndex.getRow() + 1);
                break;
            case DOWN:
                emptyCellIndex.setRow(emptyCellIndex.getRow() - 1);
                break;
            case LEFT:
                emptyCellIndex.setCol(emptyCellIndex.getCol() - 1);
                break;
            case RIGHT:
                emptyCellIndex.setCol(emptyCellIndex.getCol() + 1);
                break;
        }
    }

    private State swapEmptyCell(Direct direct, State state) { //we should return new State object respectively with method getNeighbours() realization
        State newState = state.clone();
        Index emptyCellIndex = newState.getBoardModel().getEmptyCellIndex();
        Integer[][] boardRepresentation = newState.getBoardModel().getBoardRepresentation();
        Index oldEmptyCellIndex = emptyCellIndex.clone();
        recalculateEmptyCellIndex(direct, newState);
        boardRepresentation[oldEmptyCellIndex.getCol()][oldEmptyCellIndex.getRow()] = boardRepresentation[emptyCellIndex.getCol()][emptyCellIndex.getRow()];
        boardRepresentation[emptyCellIndex.getCol()][emptyCellIndex.getRow()] = 0;
        return newState;
    }

    public List<Direct> getAllowedDirectList(State state) {
        List<Direct> allowedDirectList = new ArrayList<>();
        List<Direct> directBlockedList = getDirectBlockedList(state);
        Direct.toList().forEach(it -> {
            if (!directBlockedList.contains(it)) {
                allowedDirectList.add(it);
            }
        });
        return allowedDirectList;
    }

    public List<State> getNeighbours(State state) {
        List<State> neighbours = new ArrayList<>();
        try {
            for (Direct d : getAllowedDirectList(state)) {
                State neighbour = move(d, state);
                neighbours.add(neighbour);
            }
        }catch (DirectNotAllowedException e){
            System.out.println(e.getMessage());
        }
        return neighbours;
    }

    public boolean isTerminal(State state) {
       return Arrays.deepEquals(state.getBoardModel().getBoardRepresentation(), TERMINAL_STATE);
    }

    public Integer getDistance(State a, State b) {
        State c = b;
        int res = 0;
        while ((c != null) && (!c.equals(a))) {
            c = c.getParent();
            res++;
        }
        return res;
    }
}
