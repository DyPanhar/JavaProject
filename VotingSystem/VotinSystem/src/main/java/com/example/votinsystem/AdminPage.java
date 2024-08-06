package com.example.votinsystem;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminPage implements Initializable {
    @FXML
    private TableView<Info> tableView;
    @FXML
    private TableColumn<Info, String> idCol;
    @FXML
    private TableColumn<Info, String> nameCol;
    @FXML
    private TableColumn<Info, String> dobCol;
    @FXML
    private TableColumn<Info, String> genderCol;
    @FXML
    private TableColumn<Info, String> telCol;
    @FXML
    private TableColumn<Info, String> pwCol;
    @FXML
    private TableColumn<Info, Boolean> statusCol;
    @FXML
    private TextField nametxt;
    @FXML
    private TextField idtxt;
    @FXML
    private DatePicker dobtxt;
    @FXML
    private TextField gendertxt;
    @FXML
    private TextField teltxt;
    @FXML
    private TextField pwtxt;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static final String FILE_PATH = "voter_data.txt";

    // Switch to home Page
    public void switchToHomepage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addVoter(ActionEvent event) {
        try {
            validateInput();

            Info info = new Info(
                    idtxt.getText(),
                    nametxt.getText(),
                    dobtxt.getValue().toString(),
                    gendertxt.getText(),
                    teltxt.getText(),
                    pwtxt.getText(),
                    new SimpleBooleanProperty(false));

            // Add to TableView
            ObservableList<Info> infoObservableList = tableView.getItems();
            infoObservableList.add(info);
            tableView.setItems(infoObservableList);

            // Save to file
            saveDataToFile();
             tableView.refresh();
            // Clear the text fields after adding a voter
            clearTextFields();
        } catch (ValidationException e) {
            showAlert("Validation Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void validateInput() throws ValidationException {
        // Implement input validation logic here
        if (idtxt.getText().isEmpty() || nametxt.getText().isEmpty() || dobtxt.getValue() == null
                || gendertxt.getText().isEmpty() || teltxt.getText().isEmpty() || pwtxt.getText().isEmpty()) {
            throw new ValidationException("All fields must be filled in.");
        }
        // Add more validation as needed
    }

    private void saveDataToFile() {
        ObservableList<Info> infoList = tableView.getItems();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Info info : infoList) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%b%n",
                        info.getVoterID(), info.getName(), info.getDob(),
                        info.getGender(), info.getTel(), info.getPassword(), info.isHasVoted());

                writer.write(line);
            }
        } catch (IOException e) {
            showAlert("Error", "An error occurred while saving data to the file.", Alert.AlertType.ERROR);
        }
    }

    private void loadTableDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            List<Info> infoList = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    boolean hasVotedValue = Boolean.parseBoolean(data[6]);
                    Info info = new Info(data[0], data[1], data[2], data[3], data[4], data[5], new SimpleBooleanProperty(hasVotedValue));
                    infoList.add(info);
                }
            }

            tableView.getItems().clear();
            tableView.setItems(FXCollections.observableArrayList(infoList));
        } catch (IOException e) {
            showAlert("Error", "An error occurred while loading data from the file.", Alert.AlertType.ERROR);
        }
    }

    private void clearTextFields() {
        nametxt.clear();
        teltxt.clear();
        gendertxt.clear();
        idtxt.clear();
        pwtxt.clear();
        dobtxt.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(cellData -> cellData.getValue().voterIDProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dobCol.setCellValueFactory(cellData -> cellData.getValue().dobProperty());
        genderCol.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        telCol.setCellValueFactory(cellData -> cellData.getValue().telProperty());
        pwCol.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        statusCol.setCellValueFactory(cellData -> cellData.getValue().hasVotedProperty());

        loadTableDataFromFile();
    }

    @FXML
    void removeVoter(ActionEvent event) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            boolean confirmed = showConfirmationDialog("Confirm Removal", "Are you sure you want to remove this voter?");

            if (confirmed) {
                tableView.getItems().remove(selectedIndex);
                saveDataToFile();
            }
        } else {
            System.out.println("No voter selected for removal.");
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean showConfirmationDialog(String title, String message) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle(title);
        confirmation.setHeaderText(null);
        confirmation.setContentText(message);

        Optional<ButtonType> result = confirmation.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}
