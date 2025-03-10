module com.ss.studysystem {
    requires javafx.graphicsEmpty;
    requires password4j;
    requires Java.WebSocket;

    requires org.apache.commons.io;
    requires org.slf4j;
    requires javafx.web;
    requires org.fxmisc.richtext;
    requires org.apache.commons.imaging;
    requires net.sourceforge.plantuml;
    requires com.google.gson;
    requires java.sql;
    requires com.goxr3plus.fxborderlessscene;
    requires org.apache.commons.lang3;
    requires javafx.swing;
    requires java.prefs;
    requires reactfx;

    opens com.ss.studysystem.controller to javafx.fxml;
    exports com.ss.studysystem.controller;
    opens com.ss.studysystem to javafx.fxml;
    exports com.ss.studysystem;
    exports com.ss.studysystem.database.connection;
    opens com.ss.studysystem.database.connection to javafx.fxml;
    exports com.ss.studysystem.database.controller;
    opens com.ss.studysystem.database.controller to javafx.fxml;
    exports com.ss.studysystem.Model;
    opens com.ss.studysystem.Model to javafx.fxml, com.google.gson;
    opens com.ss.studysystem.controller.chat.request to com.google.gson;
    opens com.ss.studysystem.web.request to com.google.gson;
    exports com.ss.studysystem.controller.chat;
    opens com.ss.studysystem.controller.chat to javafx.fxml;
    exports com.ss.studysystem.controller.survey;
    opens com.ss.studysystem.controller.survey to javafx.fxml;
    exports com.ss.studysystem.controller.classroom;
    opens com.ss.studysystem.controller.classroom to javafx.fxml;
    exports com.ss.studysystem.controller.classroom.nav;
    opens com.ss.studysystem.controller.classroom.nav to javafx.fxml;
    exports com.ss.studysystem.controller.hello;
    opens com.ss.studysystem.controller.hello to javafx.fxml;
    exports com.ss.studysystem.controller.to_do_list;
    opens com.ss.studysystem.controller.to_do_list to javafx.fxml;
    exports com.ss.studysystem.controller.image_editor;
    opens com.ss.studysystem.controller.image_editor to javafx.fxml;
    exports com.ss.studysystem.UI.utils;
    opens com.ss.studysystem.UI.utils to java.desktop;
    exports com.ss.studysystem.controller.startup;
    opens com.ss.studysystem.controller.startup to javafx.fxml;
    exports com.ss.studysystem.controller.error_handler;
    opens com.ss.studysystem.controller.error_handler to javafx.fxml;
    exports com.ss.studysystem.controller.main;
    opens com.ss.studysystem.controller.main to javafx.fxml;
    exports com.ss.studysystem.controller.quiz;
    opens com.ss.studysystem.controller.quiz to javafx.fxml;
    exports com.ss.studysystem.controller.classroom.quiz;
    opens com.ss.studysystem.controller.classroom.quiz to javafx.fxml;
    exports com.ss.studysystem.controller.Homepage;
    opens com.ss.studysystem.controller.Homepage to javafx.fxml;
    exports com.ss.studysystem.controller.Profile;
    opens com.ss.studysystem.controller.Profile to javafx.fxml;
    exports com.ss.studysystem.controller.settings;
    opens com.ss.studysystem.controller.settings to javafx.fxml;
}