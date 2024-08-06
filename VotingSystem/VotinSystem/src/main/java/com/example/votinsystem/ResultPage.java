package com.example.votinsystem;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class ResultPage {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private static final String VOTE_COUNT_FILE = "result_data.txt";

    private static int democraticVote = 0;
    private static int republicVote = 0;

    @FXML
    private Label demoNum;

    @FXML
    private Label republicNum;

    @FXML
    private Label resultLabel;
    @FXML
    private Button resultBox;
    @FXML
    private Button doneBox;
    @FXML
    private  Button backBox;

    static {
        // Load initial vote counts from the file
        initializeVoteCountsFile();
        loadVoteCounts();
        setVotingStatus();
    }

    public static void voteForDemocratic() {
        democraticVote++;
        VotingStatusManager.setUserVoted(true);
        saveVoteCounts();
    }

    public static void voteForRepublic() {
        republicVote++;
        VotingStatusManager.setUserVoted(true);
        saveVoteCounts();
    }

    public static int getDemocraticVoteCount() {
        return democraticVote;
    }

    public static int getRepublicVoteCount() {
        return republicVote;
    }

    public void switchToHomepage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void initialize() {
        doneBox.setVisible(false);
    }

    public void resultCheck(Event e) {
        int democraticVoteCount = ResultPage.getDemocraticVoteCount();
        int republicVoteCount = ResultPage.getRepublicVoteCount();
        demoNum.setText(String.valueOf(democraticVoteCount));
        republicNum.setText(String.valueOf(republicVoteCount));
        if (democraticVoteCount > republicVoteCount) {
            resultLabel.setText("Democratic is the winner");
        } else if (democraticVoteCount == republicVoteCount) {
            resultLabel.setText("Both parties are equal");
        } else {
            resultLabel.setText("Republic is the winner.");
        }
        resultBox.setVisible(false);
        backBox.setVisible(false);
        doneBox.setVisible(true);
    }

    private static void saveVoteCounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VOTE_COUNT_FILE))) {
            writer.write(String.format("%d,%d", democraticVote, republicVote));
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private static void initializeVoteCountsFile() {
        try {
            File file = new File(VOTE_COUNT_FILE);
            if (!file.exists()) {
                file.createNewFile();
                saveVoteCounts(); // Save initial counts to the file
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private static void loadVoteCounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(VOTE_COUNT_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                String[] counts = line.split(",");
                if (counts.length == 2) {
                    democraticVote = Integer.parseInt(counts[0]);
                    republicVote = Integer.parseInt(counts[1]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private static void setVotingStatus() {
        VotingStatusManager.setUserVoted(democraticVote > 0 || republicVote > 0);
    }
}
