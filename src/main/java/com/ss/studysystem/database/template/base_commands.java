package com.ss.studysystem.database.template;

import java.util.List;

public interface base_commands<T> {
    boolean save(T entity);
    T get(int id);
    boolean update(T entity);
    boolean delete(int id);
    List<T> get_all_entity(int id);
}
