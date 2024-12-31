module com.ss.studysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires password4j;

    opens com.ss.studysystem.controller to javafx.fxml;
    exports com.ss.studysystem.controller;
    opens com.ss.studysystem to javafx.fxml;
    exports com.ss.studysystem;
    exports com.ss.studysystem.database.connection;
    opens com.ss.studysystem.database.connection to javafx.fxml;
    exports com.ss.studysystem.database.controller;
    opens com.ss.studysystem.database.controller to javafx.fxml;
    exports com.ss.studysystem.Model;
    opens com.ss.studysystem.Model to javafx.fxml;
    exports com.ss.studysystem.controller.chat;
    opens com.ss.studysystem.controller.chat to javafx.fxml;
    exports com.ss.studysystem.controller.classroom;
    opens com.ss.studysystem.controller.classroom to javafx.fxml;
    exports com.ss.studysystem.controller.settings;
    opens com.ss.studysystem.controller.settings to javafx.fxml;
    opens com.ss.studysystem.controller.hello to javafx.fxml; //I add this one bcuz without this I can't open Login.java
    opens com.ss.studysystem.controller.error_handler to javafx.fxml; // this one too
}