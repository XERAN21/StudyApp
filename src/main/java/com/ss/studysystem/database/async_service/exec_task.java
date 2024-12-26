package com.ss.studysystem.database.async_service;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * @implNote Includes Loader
 */
public class exec_task {
    private void executeDatabaseTask(Runnable databaseTask, String successMessage, String failureMessage, ActionEvent e, Stage st) {
        //todo dims the mainstage

        Task<Boolean> loadSceneTask = new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                try {
                    databaseTask.run();
                    return true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw ex;
                }
            }
        };

        //todo shows loading screen

        loadSceneTask.setOnSucceeded(ev -> {
            try {

                //todo remove dim, show notification, remove loading screen
                st.close();
                System.out.println(successMessage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        loadSceneTask.setOnFailed(er -> {
            try {
                //todo remove dim, show notification, remove loading screen
                System.out.println(failureMessage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        new Thread(loadSceneTask).start();
    }

}
