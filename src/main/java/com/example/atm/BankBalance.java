package com.example.atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class BankBalance {
    @FXML
    private Label label_1;
    @FXML
    private Label label_2;
    private Stage stage;
    private Scene scene;

    public void initializeData(String name, double balance) {
        label_1.setText(name);
        label_2.setText("Rs. " + balance);
    }
    public void exitWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(atm.class.getResource("first-page.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
