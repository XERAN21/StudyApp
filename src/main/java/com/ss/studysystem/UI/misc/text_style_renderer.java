package com.ss.studysystem.UI.misc;

import javafx.scene.text.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class text_style_renderer {

    public static TextFlow markdown_renderer(String markdownText, Font_size size) {
        TextFlow textFlow = new TextFlow();

        String regex = "(\\*\\*(.+?)\\*\\*|\\*(.+?)\\*|_(.+?)_)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(markdownText);

        int lastMatchEnd = 0;

        while (matcher.find()) {
            if (matcher.start() > lastMatchEnd) {
                String plainText = markdownText.substring(lastMatchEnd, matcher.start());
                textFlow.getChildren().add(create_plain_text(plainText, size));
            }

            if (matcher.group(2) != null) {
                textFlow.getChildren().add(create_bold_text(matcher.group(2), size));
            }
            else if (matcher.group(3) != null || matcher.group(4) != null) {
                String italicText = matcher.group(3) != null ? matcher.group(3) : matcher.group(4);
                textFlow.getChildren().add(create_italic_text(italicText, size));
            }

            lastMatchEnd = matcher.end();
        }

        if (lastMatchEnd < markdownText.length()) {
            String plainText = markdownText.substring(lastMatchEnd);
            textFlow.getChildren().add(create_plain_text(plainText, size));
        }

        return textFlow;
    }

    private static Text create_plain_text(String text, Font_size size) {
        Text normal_text = new Text(text);
        normal_text.setFont(Font.font("JetBrains Mono", size.getSize()));
        return normal_text;
    }

    private static Text create_bold_text(String text, Font_size size) {
        Text boldText = new Text(text);
        boldText.setFont(Font.font("JetBrains Mono", FontWeight.BOLD, size.getSize()));

        return boldText;
    }

    private static Text create_italic_text(String text, Font_size size) {
        Text italicText = new Text(text);
        italicText.setFont(Font.font("JetBrains Mono", FontWeight.NORMAL, FontPosture.ITALIC,size.getSize()));

        return italicText;
    }
}
