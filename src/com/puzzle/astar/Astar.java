package com.puzzle.astar;

import com.puzzle.agent.Agent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class Astar {
    private List<State> opened = new LinkedList<>();
    private List<State> closed = new ArrayList<>();
    private Agent agent;

    Astar(Agent agent) {
        this.agent = agent;
    }

    void search(State initialState) {
        opened.add(initialState);
        while (!opened.isEmpty()){
            State currState = getStateWithMinF(opened);
            if(agent.isTerminal(currState)) {
                System.out.println("finish");
            }
            opened.remove(currState);
            closed.add(currState);
            List<State> neighbours = agent.getNeighbours(currState);
            for(State state : neighbours) {
                if(closed.contains(currState)) {
                    continue;
                }
                int g = currState.getG() + 1;
                boolean isGBetter;
                if(!opened.contains(state)) {
                    state.setH(agent.getH(state));
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
    }

    State getStateWithMinF(List<State> open) {
        return open.stream().min(Comparator.comparing(State::getF, (f, f1) -> f <= f1 ? 1 : -1)).get();
    }
}
