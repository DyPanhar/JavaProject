package com.example.votinsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static java.awt.Color.red;

public class AdminLoginController {
    @FXML
    ImageView imageView;
    @FXML
    private TextField idtxt;
    @FXML
    private TextField pwtxt;
    @FXML
    private Label idCheck;
    @FXML
    private  Label pwCheck;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToHomepage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAminpage(ActionEvent event) throws IOException
    {
        if(idtxt.getText().equals("007") && pwtxt.getText().equals("1234"))
        {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminPage.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(idtxt.getText().isEmpty() && pwtxt.getText().equals("1234"))
        {
            idCheck.setText("Please input the id.");
            idtxt.setStyle("-fx-border-color: red;");
        }
        else if(idtxt.getText().equals("007") && pwtxt.getText().isEmpty())
        {
            pwCheck.setText("Please input the password.");
            pwtxt.setStyle("-fx-border-color: red;");
        }
        else
        {
            idCheck.setText("Please input the id.");
            pwCheck.setText("Please input the password.");
            idtxt.setStyle("-fx-border-color: red;");
            pwtxt.setStyle("-fx-border-color: red;");
        }


    }
}

