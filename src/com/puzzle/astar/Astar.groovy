package com.puzzle.astar

class Astar {
    private def opened = new LinkedList()
    private def closed = []
    private def agent

    Astar(agent) {
        this.agent = agent
    }

    def search(initialState) {
        opened << initialState
        while (!opened.isEmpty()){
            def currState = getStateWithMinF(opened)
            if(agent.isTerminal(currState)) {
                println "finish"
            }
            opened.remove(currState)
            closed << currState
            def neighbours = agent.getNeighbours(currState)
            for(State state : neighbours) {
                if(closed.contains(currState)) {
                    continue
                }
                def g = currState.getG() + 1
                def isGBetter
                if(!opened.contains(state)) {
                    state.setH(agent.getH(state))
                    opened << state
                    isGBetter = true
                } else {
                    isGBetter = g < state.getG()
                }
                if(isGBetter) {
                    state.setParent(currState)
                    state.setG(g)
                }
            }
        }
    }
//fix this sheet
    def getStateWithMinF(open) {
        def min = open[0]
        for(State state : open) {
            if(state.getF() <= min.getF()){
                min = state
            }
        }
        min
    }
}
