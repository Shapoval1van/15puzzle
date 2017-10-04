package com.puzzle

import javafx.fxml.FXML
import javafx.scene.layout.GridPane

class Controller {

    @FXML
    private GridPane gridPane

    @FXML
    void initialize(){
        def button = gridPane.getChildren().get(1)
        button.setText("ho")
    }


}
