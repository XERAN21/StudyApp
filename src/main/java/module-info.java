module com.ss.studysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.ss.studysystem.controller to javafx.fxml;
    exports com.ss.studysystem.controller;
    opens com.ss.studysystem to javafx.fxml;
    exports com.ss.studysystem;
    exports com.ss.studysystem.database.connection;
    opens com.ss.studysystem.database.connection to javafx.fxml;
    exports com.ss.studysystem.database.controller;
    opens com.ss.studysystem.database.controller to javafx.fxml;
}