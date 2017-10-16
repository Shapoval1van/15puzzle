package com.puzzle

class Agent {

    Agent(panel){
        this.panel = panel
    }

    def panel

    def move(BoardModel currentState){
        def newState = new BoardModel()
        def button = panel.getChildren().get(1)
        button.setText("ho")
        newState
    }

}
