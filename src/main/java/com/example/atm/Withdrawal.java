package com.example.atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Withdrawal {
    int balance;
    int amount;
    String name;
    Scene scene;
    Stage stage;
    @FXML
    TextField textField;
    public void initializeData(String name,int balance){
        this.balance=balance;
        this.name=name;
    }
    public void withdrawAmount(ActionEvent event) throws IOException,SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/atm_data",
                "root",
                "griffin@2006"
        );
        String executeTable = "UPDATE bank_data SET bank_balance= ? WHERE full_name=?";
        String text = textField.getText();
        amount = Integer.parseInt(text);
        if (balance > amount && amount != 0 && amount >1000) {
            balance = balance - amount;
            PreparedStatement preparedStatement = connection.prepareStatement(executeTable);
            preparedStatement.setInt(1, balance);
            preparedStatement.setString(2, name);
            int rows = preparedStatement.executeUpdate();
            showFinalPage(event);
        }else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Widthdrawal failure");
            alert.setContentText("Transaction failed");
            alert.showAndWait();
        }
    }
    public void showFinalPage(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader =new FXMLLoader(atm.class.getResource("final page.fxml"));
        Parent root=fxmlLoader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();
    }
    public void BackTrack(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(atm.class.getResource("first-page.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("ATM");
        stage.setScene(scene);
        stage.show();
    }
}

