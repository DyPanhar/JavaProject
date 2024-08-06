module com.example.votinsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.slf4j;


    opens com.example.votinsystem to javafx.fxml;
    exports com.example.votinsystem;



}