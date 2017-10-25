package com.puzzle

import com.puzzle.agent.Agent
import com.puzzle.astar.Astar
import com.puzzle.astar.State
import com.puzzle.initializer.InitializerType
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.layout.GridPane

class Controller {

    @FXML
    private GridPane gridPane
    @FXML
    private Button startButton


    private def boardModel = new BoardModel()

    @FXML
    void initialize() {
        fillBoard(InitializerType.Random)
        startButton.setOnAction(new EventHandler<ActionEvent>(){

            void handle(ActionEvent event) {
                new Astar(new Agent()).search(new State(boardModel))
            }
        })
        boardModel.getBoardRepresentation().each { println it}
//        agent.move(Direct.UP)
        println("\n++++++++++++++++++++++")
//
    }

    def fillBoard(InitializerType initializerType = null) {
        if(initializerType != null)
            boardModel.init(initializerType)
        gridPane.getChildren().clear()
        for (int i = 0; i < BoardModel.SIZE; i++) {
            for (int j = 0; j < BoardModel.SIZE; j++) {
                def value = boardModel.getBoardRepresentation()[i][j]
                if (value == 0){
                    continue
                }
                Button button = new Button(value.toString())
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE)
                gridPane.add(button, j, i)
            }
        }
        boardModel
    }
}
