package com.example.atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Deposit {
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
    public void depositAmount(ActionEvent event) throws IOException, SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/atm_data",
                "root",
                "griffin@2006"
        );
        String executeTable = "UPDATE bank_data SET bank_balance= ? WHERE full_name=?";
        String text = textField.getText();
        amount = Integer.parseInt(text);
        balance = balance + amount;
        PreparedStatement preparedStatement = connection.prepareStatement(executeTable);
        preparedStatement.setInt(1, balance);
        preparedStatement.setString(2, name);
        int rows = preparedStatement.executeUpdate();
        showFinalPage(event);
    }
    public void showFinalPage(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader =new FXMLLoader(atm.class.getResource("final page2.fxml"));
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
