package com.example.atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class validator {

    @FXML
    TextField textField;
    @FXML
    private ResultSet resultSet;
    private Stage stage;
    private Scene scene;

    public void ShowNextWindow(ActionEvent event,ResultSet result) throws IOException,SQLException{

        FXMLLoader fxmlLoader =new FXMLLoader(atm.class.getResource("second page.fxml"));
        Parent root=fxmlLoader.load();
        SecondPageController secondPageController=fxmlLoader.getController();
        secondPageController.setResultSet(result.getString("full_name"),result.getInt("bank_balance"),result.getLong("phone_no"),result.getLong("account_no"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();
    }

    public void validatePin(ActionEvent event) throws IOException, SQLException {

        String pin=textField.getText();
        Connection connection= DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/atm_data",
                "root",
                "griffin@2006"
        );
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM Bank_Data WHERE green_pin="+pin);
        if (resultSet.next()){
            ShowNextWindow(event,resultSet);
        }else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid pin");
            alert.setContentText("Entered pin is not valid");
            alert.showAndWait();

        }
    }
    public void showPin(ActionEvent event) throws IOException{
          FXMLLoader fxmlLoader =new FXMLLoader(atm.class.getResource("pin_generate.fxml"));
          Parent root=fxmlLoader.load();
          stage=(Stage)((Node)event.getSource()).getScene().getWindow();
          scene=new Scene(root,600,400);
          stage.setScene(scene);
          stage.show();
    }
}
