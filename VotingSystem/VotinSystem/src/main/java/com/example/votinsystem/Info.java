package com.example.votinsystem;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Info {
    private final SimpleStringProperty name;
    private final SimpleStringProperty voterID;
    private final SimpleStringProperty tel;
    private final SimpleStringProperty dob;
    private final SimpleStringProperty password;
    private final SimpleStringProperty gender;
    private final SimpleBooleanProperty hasVoted;

    public Info(String voterID, String name, String dob, String gender, String tel, String password, SimpleBooleanProperty hasVoted) {
        this.voterID = new SimpleStringProperty(voterID);
        this.name = new SimpleStringProperty(name);
        this.dob = new SimpleStringProperty(dob);
        this.gender = new SimpleStringProperty(gender);
        this.tel = new SimpleStringProperty(tel);
        this.password = new SimpleStringProperty(password);
        this.hasVoted = hasVoted;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getVoterID() {
        return voterID.get();
    }

    public SimpleStringProperty voterIDProperty() {
        return voterID;
    }

    public String getTel() {
        return tel.get();
    }

    public SimpleStringProperty telProperty() {
        return tel;
    }

    public String getDob() {
        return dob.get();
    }

    public SimpleStringProperty dobProperty() {
        return dob;
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public boolean isHasVoted() {
        return hasVoted.get();
    }

    public SimpleBooleanProperty hasVotedProperty() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted.set(hasVoted);
    }

    public void display() {
        System.out.println("Name: " + getName());
        System.out.println("Voter ID: " + getVoterID());
        System.out.println("Gender: " + getGender());
        System.out.println("Phone number: " + getTel());
        System.out.println("Password: " + getPassword());
        System.out.println("Date of Birth: " + getDob());
    }
}
