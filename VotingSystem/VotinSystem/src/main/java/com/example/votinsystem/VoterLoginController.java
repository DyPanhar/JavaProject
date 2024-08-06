package com.example.votinsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class VoterLoginController {
    @FXML
    ImageView imageView;
    @FXML
    private TextField idtxt;
    @FXML
    private TextField pwtxt;
    @FXML
    private Label idLabel;
    @FXML
    private Label pwLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToVoterPage(ActionEvent event) throws IOException {
        String enterID = idtxt.getText();
        String enterPw = pwtxt.getText();

        // Specify the path to your data file
//        String filePath = "D:\\Year 2\\OOP\\ProjectTest\\voter_data.txt";
        String filePath = "D:\\Year 2\\OOP\\ProjectTest\\voter_data.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Assuming each line contains user data in the format "VoterID,Name,DateOfBirth,Gender,PhoneNumber,Password,Voted"
                if (parts.length >= 3) { // Ensure "Voted" flag is present
                    String storedID = parts[0];
                    String storedPw = parts[parts.length - 2]; // Password is now third last field
                    boolean hasVoted = Boolean.parseBoolean(parts[parts.length - 1]);

                    if (enterID.equals(storedID) && enterPw.equals(storedPw)) {
                        if (enterID.isEmpty()) {
                            idLabel.setText("Please input the ID");
                            idtxt.setStyle("-fx-border-color: red;");
                        } else if (enterPw.isEmpty()) {
                            pwLabel.setText("Please input the Password.");
                            pwtxt.setStyle("-fx-border-color: red;");
                        } else if (!enterID.equals(storedID) && enterPw.equals(storedPw)) {
                            idLabel.setText("Incorrect ID");
                            idtxt.setStyle("-fx-border-color: red;");
                        } else if (enterID.equals(storedID) && !enterPw.equals(storedPw)) {
                            pwLabel.setText("Incorrect Password.");
                            pwtxt.setStyle("-fx-border-color: red;");
                        } else {
                            pwtxt.setStyle("-fx-border-color: red;");
                            pwLabel.setText("Incorrect Password.");
                            idtxt.setStyle("-fx-border-color: red;");
                            idLabel.setText("Incorrect ID");
                        }
                        if (hasVoted) {
                            // User has already voted, redirect to another page
                            VotingStatusManager.setUserVoted(true);
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VoterPage1.fxml")));
                        } else {
                            // User hasn't voted yet, proceed to the voting page
                            VotingStatusManager.setUserVoted(false);
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VoterPage.fxml")));
                            markUserAsVoted(storedID);
                        }

                        // Set up the stage and scene
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        return;
                    }
                }
            }

            // Handle the case when no matching credentials are found
            if (enterID.isEmpty()) {
                idLabel.setText("Please input the ID");
                idtxt.setStyle("-fx-border-color: red;");
            } else if (enterPw.isEmpty()) {
                pwLabel.setText("Please input the Password.");
                pwtxt.setStyle("-fx-border-color: red;");
            } else {
                pwtxt.setStyle("-fx-border-color: red;");
                pwLabel.setText("Incorrect Password.");
                idtxt.setStyle("-fx-border-color: red;");
                idLabel.setText("Incorrect ID");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately.
        }
    }

    // This method should be called when the user casts their vote
    public void markUserAsVoted(String voterID) throws IOException {
        // Specify the path to your data file
//        String filePath = "D:\\Year 2\\OOP\\ProjectTest\\voter_data.txt";
        String filePath = "D:\\Year 2\\OOP\\ProjectTest\\voter_data.txt";

        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Assuming each line contains user data in the format "VoterID,Name,DateOfBirth,Gender,PhoneNumber,Password,Voted"
                if (parts.length >= 3) { // Ensure "Voted" flag is present
                    String currentVoterID = parts[0];

                    System.out.println("Before update - Voter data line: " + line);

                    if (VotingStatusManager.isUserVoted() && currentVoterID.equals(voterID)) {
                        parts[parts.length - 1] = "true"; // Mark the user as voted
                    }

                    updatedLines.add(String.join(",", parts));
                }
            }
        }

        // Save the updated lines back to the file (append mode)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String updatedLine : updatedLines) {
                System.out.println("After update - Voter data line: " + updatedLine);
                writer.write(updatedLine);
                writer.newLine();
            }
        }
    }



    public void switchToHomepage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
    }
}