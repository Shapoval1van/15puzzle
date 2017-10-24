package com.puzzle

import com.puzzle.agent.Agent
import com.puzzle.initializer.InitializerType
import javafx.fxml.FXML
import javafx.scene.control.Button

class Controller {

    @FXML
    private def gridPane

    private def agent


    @FXML
    void initialize() {
        agent = new Agent(gridPane, fillBoard())
//        agent
    }

    def fillBoard() {
        def boardModel = new BoardModel()
        boardModel.init(InitializerType.Random)
        for (int i = 0; i < BoardModel.SIZE; i++) {
            for (int j = 0; j < BoardModel.SIZE; j++) {
                def value = boardModel.getBoardRepresentation()[i][j]
                if (value == 0 )
                    continue
                Button button = new Button(value.toString())
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE)
                gridPane.add(button, i, j)
            }
        }
        boardModel
    }
}
