package com.puzzle.agent

import com.puzzle.BoardModel
import javafx.scene.layout.GridPane

class Agent {

    private def panel
    private def boardModel
    private def emptyCellIndex

    Agent(GridPane panel, BoardModel boardModel) {
        this.panel = panel
        this.boardModel = boardModel
        emptyCellIndex = boardModel.getEmpyCellIndex()
    }

    def move(Direct direct) throws DirectNotAllowedException {
        if (!directAllowed(direct)) {
            throw new DirectNotAllowedException("Direct: $direct not alowed")
        }
        def boardRepresentation = boardModel.getBoardRepresentation()
        def oldEmptyCellIndex = emptyCellIndex.clone()
        recalculateEmptyCellIndex(direct)
        boardRepresentation[oldEmptyCellIndex.x][oldEmptyCellIndex.y] =
                boardRepresentation[emptyCellIndex.x][emptyCellIndex.y]
        boardRepresentation[emptyCellIndex.x][emptyCellIndex.y] = 0
    }

    def directAllowed(Direct direct) {
        def emptyCellIndex = boardModel.getEmpyCellIndex()
        def blockedDirectsList = []
        if (emptyCellIndex.x == 0)
            blockedDirectsList << Direct.LEFT
        else if (emptyCellIndex.x == (BoardModel.SIZE - 1))
            blockedDirectsList << Direct.RIGHT
        if (emptyCellIndex.y == 0)
            blockedDirectsList << Direct.DOWN
        else if (emptyCellIndex.y == (BoardModel.SIZE - 1))
            blockedDirectsList << Direct.UP
        blockedDirectsList.contains(direct)
    }

    def recalculateEmptyCellIndex(Direct direct) {
        switch (direct){
            case Direct.UP:
                ++emptyCellIndex.y
                break
            case Direct.DOWN:
                --emptyCellIndex.y
                break
            case Direct.LEFT:
                --emptyCellIndex.x
                break
            case Direct.RIGHT:
                --emptyCellIndex.x
                break
        }
    }
}
