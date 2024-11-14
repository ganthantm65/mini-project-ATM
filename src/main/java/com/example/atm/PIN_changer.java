package com.example.atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

public class PIN_changer {
    @FXML
    TextField textField;
    @FXML
    private long acc_no;
    private Stage stage;
    private Scene scene;

    public void initializeData(long acc_no) {
        this.acc_no = acc_no;
    }

    public void pinChanger(ActionEvent event) throws IOException, SQLException {
        int pin = Integer.parseInt(textField.getText());
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/atm_data",
                "root",
                "griffin@2006"
        );
        String executeTable = "UPDATE bank_data SET green_pin= ? WHERE account_no=?";
        String text = textField.getText();
        PreparedStatement preparedStatement = connection.prepareStatement(executeTable);
        preparedStatement.setInt(1, pin);
        preparedStatement.setLong(2, acc_no);
        int rows = preparedStatement.executeUpdate();
        showFinalPage(event);
    }

    public void showFinalPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(atm.class.getResource("final-page3.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void BackTrack(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(atm.class.getResource("first-page.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ATM");
        stage.setScene(scene);
        stage.show();

    }
}
