package com.ss.studysystem.database.async_service;

import com.ibm.icu.impl.locale.XCldrStub;
import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.misc.modal_animations;
import javafx.animation.ParallelTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @implNote Includes Loader
 * todo needs a lot of fixes
 * todo loadSceneTask always returns true,
 */


public class exec_task {

    private Consumer<Boolean> on_result;
    private Consumer<Object> on_object;
    private Stage loader;

    public Consumer<Boolean> getOn_result() {
        return on_result;
    }
    public Consumer<Object> get_on_object() {
        return on_object;
    }


    public void set_on_result(Consumer<Boolean> on_result) {
        this.on_result = on_result;
    }


    public <T> void exec_database_task(Callable<T> databaseTask, String successMessage, String failureMessage, ActionEvent e, Stage st) {
        //todo dims the mainstage
        try {

            FXMLLoader fx_spinner = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/loading_screen.fxml"));
            Parent parent_spinner = fx_spinner.load();
            this.loader = modal_builder.build_fixed_modal(st, parent_spinner);

            Task<T> loadSceneTask = new Task<>() {
                @Override
                protected T call() throws Exception {
                    try {
                        return databaseTask.call();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        throw ex;
                    }
                }
            };
            //todo shows loading screen

            loader.show();

            loadSceneTask.setOnSucceeded(ev -> {
                try {

                    //todo remove dim, show notification, remove loading screen
                    System.out.println(successMessage);

                    close_loader();
                    T result = loadSceneTask.getValue();
                    if (on_result != null) {
                        if(result != null) on_result.accept(true);
                        else on_result.accept(false);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            loadSceneTask.setOnFailed(er -> {
                try {
                    //todo remove dim, show notification, remove loading screen
                    System.out.println(failureMessage);
                    close_loader();
                    if (on_result != null) {
                        on_result.accept(false);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            new Thread(loadSceneTask).start();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void close_loader() {
        if (loader != null) {
            ParallelTransition closeAnimation = modal_animations.close_modal_w_size(loader.getScene().getRoot(), loader.getWidth(), loader.getHeight());
            closeAnimation.setOnFinished(req -> loader.close());
            closeAnimation.play();
        }
    }

    public <T> void exec_database_task_no_loader(Callable<T> databaseTask, String successMessage, String failureMessage, ActionEvent e, Stage st) {

        Task<T> loadSceneTask = new Task<>() {
            @Override
            protected T call() throws Exception {
                try {
                    return databaseTask.call();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw ex;
                }
            }
        };


        loadSceneTask.setOnSucceeded(ev -> {
            try {

                //todo remove dim, show notification, remove loading screen
                System.out.println(successMessage);
                T result = loadSceneTask.getValue();
                if (on_result != null) {
                    if(result != null) on_result.accept(true);
                    else on_result.accept(false);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        loadSceneTask.setOnFailed(er -> {
            try {
                //todo remove dim, show notification, remove loading screen
                System.out.println(failureMessage);
                if (on_result != null) {
                    on_result.accept(false);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        new Thread(loadSceneTask).start();
    }

}
