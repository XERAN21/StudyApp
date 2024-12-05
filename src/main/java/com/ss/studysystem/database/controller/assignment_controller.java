package com.ss.studysystem.database.controller;

import com.ss.studysystem.database.connection.DBConnection;
import com.ss.studysystem.Model.Assignments;

import java.util.List;

public class assignment_controller extends DBConnection<Assignments> {

    @Override
    public void save(Assignments entity) {

    }

    @Override
    public Assignments get(int id) {
        return null;
    }

    @Override
    public boolean update(Assignments entity) {
        return false;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Assignments> get_all_entity() {
        return List.of();
    }
}
