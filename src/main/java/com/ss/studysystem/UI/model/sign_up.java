package com.ss.studysystem.UI.model;

import com.ss.studysystem.UI.layouts.config_background;
import com.ss.studysystem.UI.logic.switch_scene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.function.Consumer;

public class sign_up {

    private static sign_up instance;

    private HBox root;
    private Node currentNode;
    private Node signInNode;
    private Node detailsNode;
    private Background backgroundImage;

    private sign_up() {}

    public static sign_up getInstance() {
        if (instance == null) {
            instance = new sign_up();
        }
        return instance;
    }

    public HBox getRoot() {
        return root;
    }

    public void setRoot(HBox root) {
        this.root = root;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Node getSignInNode() {
        return signInNode;
    }

    public void setSignInNode(Node signInNode) {
        this.signInNode = signInNode;
    }

    public Node getDetailsNode() {
        return detailsNode;
    }

    public void setDetailsNode(Node detailsNode) {
        this.detailsNode = detailsNode;
    }

    public Background getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Background backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void dispose() {
        if (root != null) {
            root.getChildren().clear();
            root.setBackground(null);
            root = null;
        }

        currentNode = null;
        signInNode = null;
        detailsNode = null;

        backgroundImage = null;
        instance = null;
    }


    public class Controller {

        private final switch_scene switcher = new switch_scene();
        private final sign_up signUp = sign_up.getInstance();

        public void initialize() {
            setupBackground();
            setupActions();
            alignRoot();
        }

        private void setupBackground() {
            if (signUp.getBackgroundImage() == null) {
                File imgFile = new File("/Users/thantzinlin/Downloads/Mountain Sunset Wallpaper.jpg");
                Image backgroundImage = new Image(imgFile.toURI().toString());

                Background background = config_background.get_background_w_prop(backgroundImage);
                signUp.setBackgroundImage(background);
            }
            signUp.getRoot().setBackground(signUp.getBackgroundImage());
        }

        private void setupActions() {
            bindButtonAction(signUp.getSignInNode(), "#sign_up", this::switchToDetails);
            bindButtonAction(signUp.getDetailsNode(), "#go_back", this::returnToSignIn);
            bindButtonAction(signUp.getSignInNode(), "#login_acc", this::loginAccount);
        }

        private void alignRoot() {
            HBox root = signUp.getRoot();
            root.setAlignment(Pos.CENTER);
        }

        private void bindButtonAction(Node parentNode, String buttonId, Consumer<ActionEvent> action) {
            Button button = (Button) parentNode.lookup(buttonId);
            if (button != null) {
                button.setOnAction(event -> action.accept(event));
            }
        }

        private void switchToDetails(ActionEvent event) {
            switchNode(signUp.getDetailsNode());
        }

        private void returnToSignIn(ActionEvent event) {
            switchNode(signUp.getSignInNode());
        }

        private void loginAccount(ActionEvent event) {
            try {
                switcher.switchToLoginScene(event, (Stage) signInNode.getScene().getWindow());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void switchNode(Node targetNode) {
            Platform.runLater(() -> {
                HBox root = signUp.getRoot();
                root.getChildren().remove(signUp.getCurrentNode());
                signUp.setCurrentNode(targetNode);
                root.getChildren().add(targetNode);
            });
        }
    }
}
