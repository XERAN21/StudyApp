package com.ss.studysystem.controller.chat;

import com.ss.studysystem.Model.Chatter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.net.URL;

public class chat_message_ww_img_ctrl {

    @FXML
    private VBox messages;

    @FXML
    private Text msg_date;

    @FXML
    private GridPane msg_root;

    @FXML
    private HBox hmsg_root;

    @FXML
    private Circle profile_image;

    @FXML
    private Text username;

    public void initialize() {

        File imgFile = new File("/Users/thantzinlin/Downloads/Mountain Sunset Wallpaper.jpg");
        Image img = new Image(imgFile.toURI().toString());
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);


    }


    public void set_messages(String msg, File img_file, Chatter who) {
        Platform.runLater(() -> {

            try {

                TextFlow tf = new TextFlow();
                tf.getChildren().add(new Text(msg.trim()));
                VBox wrapper = new VBox(tf);
                HBox.setHgrow(wrapper, Priority.ALWAYS);

                ContextMenu contextMenu = new ContextMenu();
                MenuItem copyItem = new MenuItem("Copy");
                copyItem.setOnAction(e -> {
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(msg);
                    clipboard.setContent(content);
                });

                contextMenu.getItems().add(copyItem);

                tf.setOnContextMenuRequested(e -> {
                    contextMenu.show(tf, e.getScreenX(), e.getScreenY());
                });

                URL path = getClass().getResource("/com/ss/studysystem/css/msg_bubble.css");
                if (path != null)
                    msg_root.getStylesheets().add(path.toExternalForm());

                messages.getChildren().addAll(wrapper);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

    }

}
