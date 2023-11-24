module com.example.votinsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.votinsystem to javafx.fxml;
    exports com.example.votinsystem;
}