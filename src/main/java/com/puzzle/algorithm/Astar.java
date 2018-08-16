package com.puzzle.algorithm;

import com.puzzle.BoardModel;
import com.puzzle.agent.Agent;
import com.puzzle.agent.Heuristic.Heuristic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Astar {

    private List<State> opened = new LinkedList<>();
    private List<State> closed = new ArrayList<>();
    private boolean isFinished = false;
    private Agent agent;

    public Astar(Agent agent) {
        this.agent = agent;
    }

    public Stack<BoardModel> search(State initialState, Heuristic heuristic) {
        opened.add(initialState);
        while (!opened.isEmpty()){
            State currState = getStateWithMinF();
            if(agent.isTerminal(currState)) {
                isFinished = true;
                return createPath(currState);
            }
            opened.remove(currState);
            closed.add(currState);
            List<State> neighbours = agent.getNeighbours(currState);
            for(State state : neighbours) {
                if(closed.contains(state)) {
                    continue;
                }
                int g = currState.getG() + agent.getDistance(currState, state);
                boolean isGBetter;
                if(!opened.contains(state)) {
                    state.setH(heuristic.getH(state));
                    opened.add(state);
                    isGBetter = true;
                } else {
                    isGBetter = g < state.getG();
                }
                if(isGBetter) {
                    state.setParent(currState);
                    state.setG(g);
                }
            }
        }
        isFinished = true;
        return new Stack<>();
    }

    State getStateWithMinF() {
        State res = null;
        int min = Integer.MAX_VALUE;
        for (State state : opened) {
            if (state.getF() < min) {
                min = state.getF();
                res = state;
            }
        }
        return res;
//        return opened.stream().min(Comparator.comparing(State::getF, (f, f1) -> f < f1 ? -1 : 1)).get();
    }

    Stack<BoardModel> createPath(State finalState) {
        Stack<BoardModel> path = new Stack<>();
        State state = finalState;
        while (state.getParent()!= null) {
            path.push(state.getBoardModel());
            state = state.getParent();
        }
        return path;
    }

    public Integer getOpenedSize() {
        return opened.size();
    }

    public Integer getClosedSize() {
        return opened.size();
    }

    public boolean isFinished() {
        return isFinished;
    }
}
