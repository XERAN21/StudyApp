module com.ss.studysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.ss.studysystem to javafx.fxml;
    exports com.ss.studysystem;
}