package com.puzzle.astar;

import com.puzzle.BoardModel;

import java.util.Arrays;

public class State {
  private BoardModel boardModel;
  private State parent;
  private int g;
  private int h;

  State(BoardModel boardModel) {
    this.boardModel = boardModel;
  }

  public BoardModel getBoardModel() {
    return boardModel;
  }

  public void setBoardModel(BoardModel boardModel) {
    this.boardModel = boardModel;
  }

  public State getParent() {
    return parent;
  }

  public void setParent(State parent) {
    this.parent = parent;
  }

  public Integer getG() {
    return g;
  }

  public void setG(Integer g) {
    this.g = g;
  }

  public Integer getH() {
    return h;
  }

  public void setH(Integer h) {
    this.h = h;
  }

  public Integer getF() {
    return g + h;
  }

  @Override
  public boolean equals(Object state) {
      return Arrays.deepEquals(((State)state).getBoardModel().getBoardRepresentation(),this.getBoardModel().getBoardRepresentation());
  }

  @Override
  public State clone(){
    return new State(this.boardModel.clone());
  }
}