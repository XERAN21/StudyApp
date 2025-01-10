package com.ss.studysystem.UI.logic;

import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.model.login_mdl;
import com.ss.studysystem.UI.model.sign_up_mdl;
import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class switch_scene {

    private static final Logger logger = Logger.getLogger(switch_scene.class.getName());

    public void switchToSignUpScene(ActionEvent event, Stage mainStage) {

        logger.info("Loading sign-up FXML files...");

        sign_up_mdl signUpConfig = sign_up_mdl.getWeakInstance();
        sign_up_mdl.Controller signUpController = signUpConfig.new Controller();

        Task<HBox> loadSceneTask = new Task<>() {
            @Override
            protected HBox call() throws Exception {

                try {
                    signUpConfig.setSignInNode(loadFXML(getClass().getResource("/com/ss/studysystem/Fxml/sign_up.fxml").toString()));
                    signUpConfig.setDetailsNode(loadFXML(getClass().getResource("/com/ss/studysystem/Fxml/sign_up_details.fxml").toString()));

                    signUpConfig.setCurrentNode(signUpConfig.getSignInNode());

                    Scene currentScene = mainStage.getScene();
                    HBox root = new HBox();

                    root.setPrefWidth(currentScene.getWidth());
                    root.setPrefHeight(currentScene.getHeight());

                    root.getChildren().add(signUpConfig.getCurrentNode());

                    signUpConfig.setRoot(root);
                    return root;
                } catch (Exception ex) {
                    logger.severe("Error while loading FXML files: " + ex.getMessage());
                    throw ex;
                }
            }
        };

        configureTask(loadSceneTask, event, mainStage, signUpController::initialize);
    }

    public void switchToLoginScene(ActionEvent event, Stage mainStage) {

        login_mdl loginConfig = login_mdl.getInstance();

        Task<HBox> loadSceneTask = new Task<>() {
            @Override
            protected HBox call() throws Exception {
                Parent loginNode = loadFXML(getClass().getResource("/com/ss/studysystem/Fxml/Entry_page/login.fxml").toString());

                Scene currentScene = mainStage.getScene();
                HBox root = new HBox();
                if (currentScene.getWidth() > 800 && currentScene.getHeight() > 600) {
                    root.setPrefWidth(currentScene.getWidth());
                    root.setPrefHeight(currentScene.getHeight());
                } else {
                    root.setPrefWidth(800);
                    root.setPrefHeight(600);
                }
                root.setBackground(loginConfig.getBackground_image());
                root.getChildren().add(loginNode);

                root.setAlignment(Pos.CENTER);

//                Platform.runLater(() -> {
//                    config_position.center_node(mainStage, loginNode);
//                    mainStage.widthProperty().addListener((obs, oldVal, newVal) -> config_position.center_node(mainStage, loginNode));
//                    mainStage.heightProperty().addListener((obs, oldVal, newVal) -> config_position.center_node(mainStage, loginNode));
//                });

                return root;
            }
        };

        configureTask(loadSceneTask, event, mainStage,
                () -> {
                    HBox root = loadSceneTask.getValue();
                    Node node = root.getChildrenUnmodifiable().get(0);

//                    Platform.runLater(() -> {
//                        config_position.center_node(mainStage, node);
//                        mainStage.widthProperty().addListener((obs, oldVal, newVal) -> config_position.center_node(mainStage, node));
//                        mainStage.heightProperty().addListener((obs, oldVal, newVal) -> config_position.center_node(mainStage, node));
//                    });
                });
    }

    public void switch_to_survey(ActionEvent event, Stage mainStage) {
        try {

            Task<Parent> loadSceneTask = new Task<>() {
                @Override
                protected Parent call() throws Exception {
                    return loadFXML(getClass().getResource("/com/ss/studysystem/Fxml/fourinone.fxml").toString());
                }
            };

            configureTask(loadSceneTask, event, mainStage, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Region open_chat_view(ActionEvent event, Stage mainStage) {
        try {
            FXMLLoader fx_spinner = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/loading_screen.fxml"));
            Parent parent_spinner = fx_spinner.load();
            Stage loader = modal_builder.build_fixed_modal(mainStage, parent_spinner);
            PauseTransition pause = new PauseTransition(Duration.millis(150));
            pause.setOnFinished(done -> loader.close());

            Task<Region> loadSceneTask = new Task<>() {
                @Override
                protected Region call() throws Exception {
                    FXMLLoader chat_ = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/chat_is_this_real/chat_main.fxml"));
                    return chat_.load();
                }
            };

            loader.show();

            loadSceneTask.setOnSucceeded(workerStateEvent -> {
                try {
                    Region loadedScene = loadSceneTask.getValue();
                    if (loadedScene != null) {

                        pause.play();


                    } else {
                        throw new Exception("Loaded scene is null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    pause.play();
                }
            });

            loadSceneTask.setOnFailed(workerStateEvent -> {
                pause.play();
                Throwable exception = loadSceneTask.getException();
                System.out.println("Error loading scene: " + exception.getMessage());
                exception.printStackTrace();
            });

            Thread thread = new Thread(loadSceneTask);
            thread.setDaemon(true);
            thread.start();

            return loadSceneTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Region open_class_view(ActionEvent event, Stage mainStage) {
        try {
            FXMLLoader fx_spinner = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/loading_screen.fxml"));
            Parent parent_spinner = fx_spinner.load();
            Stage loader = modal_builder.build_fixed_modal(mainStage, parent_spinner);
            PauseTransition pause = new PauseTransition(Duration.millis(150));
            pause.setOnFinished(done -> loader.close());

            Task<Region> loadSceneTask = new Task<>() {
                @Override
                protected Region call() throws Exception {
                    FXMLLoader chat_ = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/classroom/classroom_view.fxml"));
                    return chat_.load();
                }
            };

            loader.show();

            loadSceneTask.setOnSucceeded(workerStateEvent -> {
                try {
                    Region loadedScene = loadSceneTask.getValue();
                    if (loadedScene != null) {

                        pause.play();


                    } else {
                        throw new Exception("Loaded scene is null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    pause.play();
                }
            });

            loadSceneTask.setOnFailed(workerStateEvent -> {
                pause.play();
                Throwable exception = loadSceneTask.getException();
                System.out.println("Error loading scene: " + exception.getMessage());
                exception.printStackTrace();
            });

            Thread thread = new Thread(loadSceneTask);
            thread.setDaemon(true);
            thread.start();

            return loadSceneTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private <T extends Parent> void configureTask(Task<T> task, ActionEvent event,
                                                  Stage mainStage, Runnable onSuccessAction) {
        task.setOnSucceeded(e -> {
            try {

                Stage stage;

                if (event != null) {
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                } else {
                    stage = mainStage;
                }


                Parent root = task.getValue();
                Scene newScene = new Scene(root);

                if (stage.getStyle() == StageStyle.TRANSPARENT) {
                    stage.close();

                    stage = new Stage();
                    stage.initStyle(StageStyle.DECORATED);

                    stage.show();
                } else {
                    stage.show();
                }

                stage.setScene(newScene);
                stage.setMinWidth(800);
                stage.setMinHeight(600);

                stage.show();


                if (onSuccessAction != null)
                    onSuccessAction.run();
                logger.info("Switched");
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Error while setting up the scene.", ex);
            }
        });

        task.setOnFailed(e ->

        {
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

        logger.info("Successfully loaded scene.");
        return new FXMLLoader(resource).load();
    }
}
