module com.ss.studysystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ss.studysystem to javafx.fxml;
    exports com.ss.studysystem;
}