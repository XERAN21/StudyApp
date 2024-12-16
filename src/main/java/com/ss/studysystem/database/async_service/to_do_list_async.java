package com.ss.studysystem.database.async_service;

import com.ss.studysystem.Model.To_Do_List;
import com.ss.studysystem.database.controller.to_do_list_controller;
import javafx.concurrent.Service;
import javafx.concurrent.Task;


public class to_do_list_async extends Service<Boolean> {
    private To_Do_List toDoList;
    private int[] insertedId;
    private Operation operation;

    public to_do_list_async(To_Do_List toDoList, int[] insertedId) {
        this.toDoList = toDoList;
        this.insertedId = insertedId;
    }

    public to_do_list_async(To_Do_List toDoList) {
        this.toDoList = toDoList;
    }

    public void set_operation(Operation op){
        this.operation = op;
    }

    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                switch (operation){
                    case INSERT:
                        return to_do_list_controller.create_to_do_list(toDoList, insertedId);
                    case DELETE:
                       return to_do_list_controller.delete_to_do_list(toDoList.getTo_do_list());
                    case UPDATE:
                        return  to_do_list_controller.update_task_completion(toDoList.getTo_do_list(), toDoList.getIs_complete());
                    default:
                        return null;
                }
            }
        };
    }
}
