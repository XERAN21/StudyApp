package com.ss.studysystem.controller.chat;

import com.ss.studysystem.Model.Chatter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;

public class chat_bubble_gen {
    public Node generate_chat_msg(Chatter who, String msg) {

        try {
            Node bubble;
            FXMLLoader loader;

            switch (who) {
                case SELF:
                    URL self_path = getClass().getResource("/com/ss/studysystem/Fxml/chat_is_this_real/msg_bubble_self.fxml");
                    loader = new FXMLLoader(self_path);
                    bubble = loader.load();
                    break;
                case SENDER:
                    URL path = getClass().getResource("/com/ss/studysystem/Fxml/chat_is_this_real/msg_bubble.fxml");
                    loader = new FXMLLoader(path);
                    bubble = loader.load();
                    break;
                default:
                    throw new IOException("Error loading messages");
            }

            chat_message_ww_img_ctrl msg_ctrl = loader.getController();
            msg_ctrl.set_messages(msg, null, Chatter.SENDER);

            return bubble;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
