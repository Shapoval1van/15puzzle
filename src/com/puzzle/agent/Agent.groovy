package com.puzzle.agent

import com.puzzle.BoardModel
import com.puzzle.astar.State

class Agent {
    private final terminateState = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 0]]

    def move(Direct direct, State state) throws DirectNotAllowedException {
        if (getDirectBlockedList(state).contains(direct)) {
            throw new DirectNotAllowedException("Direct: $direct not alowed")
        }
        swapEmptyCell(direct, state)
    }

    private def getDirectBlockedList(State state) {
        def boardModel = state.getBoardModel()
        def blockedDirectsList = []
        def emptyCellIndex = boardModel.getEmptyCellIndex()
        if (emptyCellIndex.col == 0)
            blockedDirectsList << Direct.LEFT
        else if (emptyCellIndex.col == (BoardModel.SIZE - 1))
            blockedDirectsList << Direct.RIGHT
        if (emptyCellIndex.row == 0)
            blockedDirectsList << Direct.DOWN
        else if (emptyCellIndex.row == (BoardModel.SIZE - 1))
            blockedDirectsList << Direct.UP
        blockedDirectsList
    }

    private def recalculateEmptyCellIndex(Direct direct, State state) {
        def boardModel = state.getBoardModel()
        def emptyCellIndex = boardModel.getEmptyCellIndex()
        switch (direct) {
            case Direct.UP:
                ++emptyCellIndex.row
                break
            case Direct.DOWN:
                --emptyCellIndex.row
                break
            case Direct.LEFT:
                --emptyCellIndex.col
                break
            case Direct.RIGHT:
                ++emptyCellIndex.col
                break
        }
    }

    private def swapEmptyCell(Direct direct, State state) {
        def newState = state.clone()
        def boardModel = newState.getBoardModel()
        def emptyCellIndex = boardModel.getEmptyCellIndex()
        def boardRepresentation = boardModel.getBoardRepresentation()
        def oldEmptyCellIndex = emptyCellIndex.clone()
        recalculateEmptyCellIndex(direct, newState)
        boardRepresentation[oldEmptyCellIndex.row][oldEmptyCellIndex.col] = boardRepresentation[emptyCellIndex.row][emptyCellIndex.col]
        boardRepresentation[emptyCellIndex.row][emptyCellIndex.col] = 0
        newState
    }

    def getAllowedDirectList(State state) {
        def allowedDirectList = []
        def directBlockedList = getDirectBlockedList(state)
        Direct.toList().each {
            if (!directBlockedList.contains(it)) {
                allowedDirectList << it
            }
        }
        allowedDirectList
    }

    def getNeighbours(State state) {
        def neighbours = []
        for (Direct d : getAllowedDirectList(state)) {
            State neighbour = move(d, state)
            neighbour.setH(getH(state))
            neighbours << neighbour
        }
        neighbours
    }

    def getH(State currState) {
        def boardRepresentation = currState.getBoardModel().getBoardRepresentation()
        def result = 0
        for (int i = 0; i < BoardModel.SIZE; i++) {
            for (int j = 0; j < BoardModel.SIZE; j++) {
                if (boardRepresentation[i][j] != terminateState[i][j]) {
                    result++
                }
            }
        }
        result
    }

    def isTerminal(State state) {
        for (int i = 0; i < BoardModel.SIZE; i++) {
            for (int j = 0; j < BoardModel.SIZE; j++) {
                if(state.getBoardModel().getBoardRepresentation()[i][j] != terminateState[i][j]){
                    return false
                }
            }
        }
        return true
    }
}
