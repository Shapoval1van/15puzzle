package com.puzzle

import com.puzzle.agent.Agent
import com.puzzle.agent.Heuristic.ManhattanAndLinearHeuristic
import com.puzzle.algorithm.Astar
import com.puzzle.algorithm.State
import com.puzzle.initializer.InitializerType
import javafx.application.Platform
import javafx.concurrent.Task
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.GridPane

class Controller {

    @FXML
    private GridPane gridPane
    @FXML
    private Button startButton
    @FXML
    private Label openedListCount
    @FXML
    private Label closedListCount
    @FXML
    private Label step

    private def boardModel = new BoardModel()

    @FXML
    void initialize() {
        fillBoard(InitializerType.Shift)
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            void handle(ActionEvent event) {
                def astar = new Astar(new Agent())
                def path = astar.search(new State(boardModel), new ManhattanAndLinearHeuristic())
                step.setText("Step = " + path.size())
                openedListCount.setText("Op=" + astar.getOpenedSize())
                closedListCount.setText("Cl=" + astar.getClosedSize())
                showPath(path)
            }
        })
    }

    private def fillBoard(InitializerType initializerType = null) {
        if (initializerType != null)
            boardModel.init(initializerType)
        gridPane.getChildren().clear()
        for (int i = 0; i < BoardModel.SIZE; i++) {
            for (int j = 0; j < BoardModel.SIZE; j++) {
                def value = boardModel.getBoardRepresentation()[i][j]
                if (value == 0) {
                    continue
                }
                Button button = new Button(value.toString())
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE)
                gridPane.add(button, j, i)
            }
        }
    }

    private def showPath(path){
        Task<Void> task = new Task<Void>(){
            @Override
            Void call() throws Exception {
                while (!path.empty()) {
                    boardModel = path.pop()
                    Platform.runLater({ fillBoard() })
                    Thread.sleep(1000)
                }
            }
        }
        Thread th = new Thread(task)
        th.start()
    }
}
