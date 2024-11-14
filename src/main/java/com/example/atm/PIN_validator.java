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


public class PIN_validator {
    @FXML
    TextField textField;
    private long account_no;
    private Stage stage;
    private Scene scene;
    public void initializeData(long account_no){
        this.account_no=account_no;
    }
    public void validateAcc(ActionEvent event) throws IOException, SQLException {
        long acc_no=Long.parseLong(textField.getText());
        if(acc_no==account_no){
            FXMLLoader fxmlLoader = new FXMLLoader(atm.class.getResource("pin-changer.fxml"));
            Parent root = fxmlLoader.load();
            PIN_changer pinChanger=fxmlLoader.getController();
            pinChanger.initializeData(acc_no);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        }else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid account no");
            alert.setContentText("Entered account number is not valid");
            alert.showAndWait();

        }
    }
}
