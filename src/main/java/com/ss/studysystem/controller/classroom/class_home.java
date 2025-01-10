package com.ss.studysystem.controller.classroom;

import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.cnf.user_cnf;
import com.ss.studysystem.database.async_service.exec_task;
import com.ss.studysystem.database.controller.classroom_controller;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

public class class_home {

    @FXML
    private Text classname;

    @FXML
    private VBox home_root;

    @FXML
    private VBox home_view1;

    exec_task exe = new exec_task();

    user_cnf user = user_cnf.get_instance();

    public void set_text(String title) {
        Platform.runLater(() -> {
            classname.setText(title);
            if (title.matches("Your Classes"))
                init_view();
        });
    }

    PauseTransition pause = new PauseTransition(Duration.millis(150));

    @FXML
    void initialize() {
        user.load();
        final List<Classrooms>[] owned = new List[1];
        Callable<List<Classrooms>> get_user_ownded_classes = new Callable<List<Classrooms>>() {
            @Override
            public List<Classrooms> call() throws Exception {
                owned[0] = classroom_controller.get_owned_classrooms(user.getUser().getId());
                return owned[0];
            }
        };

        exe.set_on_result(result -> {
            if (result) {
                for (Classrooms c : owned[0]) {
                    create_classes(c);
                }
            }
        });

        exe.exec_database_task_no_loader(get_user_ownded_classes,
                "s", "f", null, null);


    }

    void init_view() {
        try {
            URL join = getClass().getResource("/com/ss/studysystem/Fxml/classroom/join_ver2.fxml").toURI().toURL();
            FXMLLoader join_loader = new FXMLLoader(join);
            Node join_node = join_loader.load();
            joinclass cc = join_loader.getController();
            home_root.getChildren().add(join_node);
            cc.setOn_class(result -> {
                pause.setOnFinished(fin ->
                        create_classes(result));
                pause.play();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void create_classes(Classrooms classrooms) {
        try {
            URL join = getClass().getResource("/com/ss/studysystem/Fxml/singleclassview.fxml").toURI().toURL();
            FXMLLoader join_loader = new FXMLLoader(join);
            Node join_node = join_loader.load();
            classroom_item ci = join_loader.getController();
            ci.init(classrooms);
            home_root.getChildren().add(join_node);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
