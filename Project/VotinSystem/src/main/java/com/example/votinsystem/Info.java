package com.example.votinsystem;

import javafx.beans.property.SimpleStringProperty;

public class Info {
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty voterID = new SimpleStringProperty();
    private final SimpleStringProperty tel = new SimpleStringProperty();
    private final SimpleStringProperty dob = new SimpleStringProperty();
    private final SimpleStringProperty password = new SimpleStringProperty();
    private final SimpleStringProperty gender = new SimpleStringProperty();

    public Info(){};

    public Info(String name , String dob , String voterID , String gender, String password , String tel)
    {
        setName(name);
        setDob(dob);
        setGender(gender);
        setTel(tel);
        setVoterID(voterID);
        setPassword(password);
    }
    public Info(String name  , String voterID , String gender, String password , String tel)
    {
        setName(name);
        setGender(gender);
        setTel(tel);
        setVoterID(voterID);
        setPassword(password);
    }


    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getVoterID() {
        return voterID.get();
    }

    public SimpleStringProperty voterIDProperty() {
        return voterID;
    }

    public void setVoterID(String voterID) {
        this.voterID.set(voterID);
    }

    public String getTel() {
        return tel.get();
    }

    public SimpleStringProperty telProperty() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }

    public String getDob() {
        return dob.get();
    }

    public SimpleStringProperty dobProperty() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public  void display()
    {
        System.out.println("Name : " + name);
        System.out.println("Voter id : " + voterID);
        System.out.println("Gender : " + gender);
        System.out.println("Phone number" + tel);
        System.out.println("Password : " + password);
        System.out.println("Date of Birth : " + dob);
    }
}

