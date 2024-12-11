package com.ss.studysystem.UI.logic;

import com.ss.studysystem.UI.layouts.config_position;
import com.ss.studysystem.UI.model.login;
import com.ss.studysystem.UI.model.sign_up;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class switch_scene {

    private static final Logger logger = Logger.getLogger(switch_scene.class.getName());

    public void switchToSignUpScene(ActionEvent event, Stage mainStage) {

        logger.info("Loading sign-up FXML files...");

        sign_up signUpConfig = sign_up.getInstance();
        sign_up.Controller signUpController = signUpConfig.new Controller();

        Task<HBox> loadSceneTask = new Task<>() {
            @Override
            protected HBox call() throws Exception {

                try {
                    signUpConfig.setSignInNode(loadFXML("/com/ss/studysystem/Fxml/sign_up.fxml"));
                    signUpConfig.setDetailsNode(loadFXML("/com/ss/studysystem/Fxml/sign_up_details.fxml"));

                    signUpConfig.setCurrentNode(signUpConfig.getSignInNode());

                    Scene currentScene = mainStage.getScene();
                    HBox root = new HBox();

                    root.setPrefWidth(currentScene.getWidth());
                    root.setPrefHeight(currentScene.getHeight());

                    root.getChildren().add(signUpConfig.getCurrentNode());

                    signUpConfig.setRoot(root);
                    return root;
                } catch (Exception ex){
                    logger.severe("Error while loading FXML files: " + ex.getMessage());
                    throw ex;
                }
            }
        };

        configureTask(loadSceneTask, event, mainStage, signUpController::initialize);
    }


    public void switchToLoginScene(ActionEvent event, Stage mainStage) {

        login loginConfig = login.getInstance();

        Task<AnchorPane> loadSceneTask = new Task<>() {
            @Override
            protected AnchorPane call() throws Exception {
                Parent loginNode = loadFXML("/com/ss/studysystem/Fxml/login.fxml");

                Scene currentScene = mainStage.getScene();
                AnchorPane root = new AnchorPane();
                root.setPrefWidth(currentScene.getWidth());
                root.setPrefHeight(currentScene.getHeight());
                root.setBackground(loginConfig.getBackground_image());
                root.getChildren().add(loginNode);

                return root;
            }
        };

        configureTask(loadSceneTask, event, mainStage,
                () -> {
            AnchorPane root = loadSceneTask.getValue();
            Node node = root.getChildrenUnmodifiable().get(0);
            config_position.center_node(mainStage, node);

            Platform.runLater(() -> {
                mainStage.widthProperty().addListener((obs, oldVal, newVal) -> config_position.center_node(mainStage, node));
                mainStage.heightProperty().addListener((obs, oldVal, newVal) -> config_position.center_node(mainStage, node));
            });
        });
    }

    private <T extends Parent> void configureTask(Task<T> task, ActionEvent event,
                                                  Stage mainStage, Runnable onSuccessAction) {
        task.setOnSucceeded(e -> {
            try {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = task.getValue();
                Scene newScene = new Scene(root);

                stage.setScene(newScene);
                stage.setMinWidth(800);
                stage.setMinHeight(600);
                stage.show();

                onSuccessAction.run();
                logger.info("Switched to sign-up scene.");
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Error while setting up the scene.", ex);
            }
        });

        task.setOnFailed(e -> {
            Throwable throwable = task.getException();
            logger.log(Level.SEVERE, "Scene loading task failed.", throwable);
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private Parent loadFXML(String resourcePath) throws Exception {
        URL resource = getClass().getResource(resourcePath);
        if (resource == null) {
            throw new IllegalArgumentException("FXML file not found: " + resourcePath);
        }

        logger.info("Successfully loaded sign-up scene.");
        return new FXMLLoader(resource).load();
    }
}
