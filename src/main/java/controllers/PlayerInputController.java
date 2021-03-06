package controllers;

import app.Server;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PlayerInputController {
    @FXML
    private Pane playerInputPane;
    @FXML
    private TextField player1Name, player2Name;

    @FXML
    public void saveNames() {
        String name1 = "n0n4m3";
        String name2 = "n0n4m3";
        if (!player1Name.getText().equals(""))
            name1 = player1Name.getText();
        if (!player2Name.getText().equals(""))
            name2 = player2Name.getText();

        Server.getInstance().setPlayerNames(name1, name2);
    }

    @FXML
    public void startGame() throws IOException {
        saveNames();
        Pane tableViewPane = FXMLLoader.load(getClass().getResource("/tableview/tableview.fxml"));
        playerInputPane.getChildren().setAll(tableViewPane);
        Server.getInstance().startGame();
    }
}
