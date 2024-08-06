package com.example.votinsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class VoterPage {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private CheckBox republicBox;

    @FXML
    private CheckBox democraticBox;

    @FXML
    private Button voteBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button doneBtn;

    // Switch to home Page
    public void switchToHomepage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private ResultPage resultPage = new ResultPage();

    public void initialize() {
        doneBtn.setVisible(false);

    }


    public void Check(ActionEvent event) throws Exception {
        System.out.println("Before voting check - isUserVoted: " + VotingStatusManager.isUserVoted());

        if (republicBox.isSelected()) {
            ResultPage.voteForRepublic();
            System.out.println("republicVote: " + ResultPage.getRepublicVoteCount());
            VotingStatusManager.setUserVoted(true);
            System.out.println("After voting - isUserVoted: " + VotingStatusManager.isUserVoted());
            doneBtn.setVisible(true);
            backBtn.setVisible(false);
            voteBtn.setVisible(false);
        } else if (democraticBox.isSelected()) {
            ResultPage.voteForDemocratic();
            System.out.println("democraticVote: " + ResultPage.getDemocraticVoteCount());
            VotingStatusManager.setUserVoted(true);
            System.out.println("After voting - isUserVoted: " + VotingStatusManager.isUserVoted());
            doneBtn.setVisible(true);
            backBtn.setVisible(false);
            voteBtn.setVisible(false);
        } else {
            System.out.println("Please select before vote.");
            VotingStatusManager.setUserVoted(false);
            System.out.println("After voting - isUserVoted: " + VotingStatusManager.isUserVoted());
        }
    }

}
