package com.example.atm;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class SecondPageController{
    private String name;
    private int balance;
    private long phone,account;
    private Stage stage;
    private Scene scene;

    public void setResultSet(String name,int balance,long phone,long account) {
        this.account=account;
        this.balance=balance;
        this.phone=phone;
        this.name=name;
    }

    public void getBalance(ActionEvent event) throws IOException {
        System.out.println("Getting balance for: " + name + ", Amount: " + balance);
        showBalance(balance, name, event);
    }

    public void showBalance(int balance,String name,ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader =new FXMLLoader(atm.class.getResource("balance.fxml"));
        Parent root=fxmlLoader.load();
        BankBalance bankBalance = fxmlLoader.getController();
        bankBalance.initializeData(name,balance);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();
    }
    public void showWithdrawal(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(atm.class.getResource("withdraw.fxml"));
        Parent root = fxmlLoader.load();
        Withdrawal withdrawal = fxmlLoader.getController();
        withdrawal.initializeData(name, balance);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
    public void showDeposit(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(atm.class.getResource("deposit.fxml"));
        Parent root = fxmlLoader.load();
        Deposit deposit = fxmlLoader.getController();
        deposit.initializeData(name, balance);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
    public void showPINChanger(ActionEvent event)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(atm.class.getResource("pin-validation.fxml"));
        Parent root = fxmlLoader.load();
        PIN_validator pinValidator=fxmlLoader.getController();
        pinValidator.initializeData(account);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
