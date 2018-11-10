package sample;

import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    @FXML
    private AnchorPane menuPane;

    public void startGame() throws IOException {
        Pane tableViewPane = FXMLLoader.load(getClass().getResource("/tableview/tableview.fxml"));
        menuPane.getChildren().setAll(tableViewPane);

    }

    public void viewHighscore() throws IOException {
        AnchorPane highscorePane = FXMLLoader.load(getClass().getResource("/highscore/highscore.fxml"));
        menuPane.getChildren().setAll(highscorePane);
    }

    public void quitGame() {
        System.exit(0);
    }
}