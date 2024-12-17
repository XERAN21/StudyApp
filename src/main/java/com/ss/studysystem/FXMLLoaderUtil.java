package com.ss.studysystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Objects;

public class FXMLLoaderUtil {

    public void loadFXMLFiles() {

        try {
            // Get the directory in the resources folder
            URL resource = getClass().getResource("/com/ss/studysystem/Fxml/");
            if (resource == null) {
                throw new IOException("Directory not found: /com/ss/studysystem/Fxml/");
            }

            // Convert the resource URL to a Path
            Path folderPath = Paths.get(resource.toURI());

            // Loop through files in the directory
            Files.list(folderPath)
                    .filter(Files::isRegularFile)                     // Only regular files
                    .filter(file -> file.toString().endsWith(".fxml")) // Filter for .fxml files
                    .forEach(file -> {
                        try {
                            // Load each FXML file
                            FXMLLoader loader = new FXMLLoader(file.toUri().toURL());
                            Parent root = loader.load();

                            // Add the loaded Parent to your scene or a layout
                            System.out.println("Loaded FXML: " + file.getFileName());
                            // Example: Add the loaded Parent to a VBox
                            // vbox.getChildren().add(root);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
