
package com.puzzle.astar

import com.puzzle.BoardModel

class State {
  private def boardModel
  private def parent
  private int g
  private int h

  State(boardModel) {
    this.boardModel = boardModel
    parent == null
  }

  def getBoardModel() {
    return boardModel
  }

  void setBoardModel(boardModel) {
    this.boardModel = boardModel
  }

  def getParent() {
    return parent
  }

  void setParent(parent) {
    this.parent = parent
  }

  def getG() {
    return g
  }

  void setG(g) {
    this.g = g
  }

  def getH() {
    return h
  }

  void setH(h) {
    this.h = h
  }

  def getF() {
    g + h
  }

  @Override
  boolean equals(Object state) {
    for (int i = 0; i < BoardModel.SIZE; i++) {
      for (int j = 0; j < BoardModel.SIZE; j++) {
        if(this.getBoardModel().getBoardRepresentation()[i][j] != ((State)state).getBoardModel().getBoardReperesentation()[i][j]){
          return false
        }
      }
    }
    return true
  }

  @Override
  protected State clone() throws CloneNotSupportedException {
    return new State(this.boardModel.clone())
  }
}