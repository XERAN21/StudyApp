package com.ss.studysystem.controller.chat;

import com.ss.studysystem.Model.Chatter;
import com.ss.studysystem.UI.misc.text_style_renderer;
import com.ss.studysystem.UI.misc.Font_size;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
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

//        File imgFile = new File("/Users/thantzinlin/Downloads/Mountain Sunset Wallpaper.jpg");
//        Image img = new Image(imgFile.toURI().toString());
//        ImageView imageView = new ImageView(img);
//        imageView.setFitWidth(300);
//        imageView.setPreserveRatio(true);


    }


    public void set_messages(String msg, File img_file, Chatter who) {
        Platform.runLater(() -> {

            try {

                TextFlow tf = text_style_renderer.markdown_renderer(msg.trim(), Font_size.L);
//                Text inner_msg = new Text(msg.trim());
//                inner_msg.setFont(new Font(Font_size.L.getSize()));
//                tf.getChildren().add(inner_msg);
                StackPane wrapper = new StackPane(tf);
                HBox.setHgrow(wrapper, Priority.ALWAYS);

//                ContextMenu contextMenu = new ContextMenu();
//                MenuItem copyItem = new MenuItem("Copy");
//                copyItem.setOnAction(e -> {
//                    Clipboard clipboard = Clipboard.getSystemClipboard();
//                    ClipboardContent content = new ClipboardContent();
//                    content.putString(msg);
//                    clipboard.setContent(content);
//                });
//
//                contextMenu.getItems().add(copyItem);
//
//                tf.setOnContextMenuRequested(e -> {
//                    contextMenu.show(tf, e.getScreenX(), e.getScreenY());
//                });


                //todo css
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
