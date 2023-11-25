package com.example.votinsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminPage implements Initializable {
    @FXML
    private TableView<Info>tableView;
    @FXML
    private TableColumn<Info , String> nameCol;
    @FXML
    private  TableColumn<Info , String> telCol;
    @FXML
    private  TableColumn<Info , String> genderCol;
    @FXML
    private  TableColumn<Info , String> pwCol;
    @FXML
    private  TableColumn<Info , String> idCol;
    @FXML
    private  TableColumn<Info , String> dobCol;
    @FXML
    private TextField nametxt;
    @FXML
    private  TextField idtxt;
   @FXML
   private DatePicker dobtxt;
    @FXML
    private  TextField gendertxt;
    @FXML
    private TextField teltxt;
    @FXML
    private  TextField pwtxt;


    private Stage stage;
    private Scene scene;
    private Parent root;

    // Switch to home Page
    public void switchToHomepage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void addVoter(ActionEvent event) {     // Add a voter
        Info info = new Info(
                nametxt.getText(),
                teltxt.getText(),
                gendertxt.getText(),
                dobtxt.getValue().toString(), // Assuming dobtxt is a DatePicker
                idtxt.getText(),
                pwtxt.getText()
        );
        info.display();
        ObservableList<Info> infoObservableList = tableView.getItems();
        infoObservableList.add(info);
        tableView.setItems(infoObservableList);

        // Clear the text fields after adding a voter
        nametxt.clear();
        teltxt.clear();
        gendertxt.clear();
        idtxt.clear();
        pwtxt.clear();
        // Clear the DatePicker if it is used
        dobtxt.setValue(null);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the TableView columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("voterID"));
        pwCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));

        // Initialize the TableView with an empty ObservableList
        tableView.setItems(FXCollections.observableArrayList());
    }

    @FXML
    void removeVoter(ActionEvent event) {   // removeVoter
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }
}
