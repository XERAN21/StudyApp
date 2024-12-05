package com.ss.studysystem.database.template;

import java.util.List;

public interface base_commands<T> {
    void save(T entity);
    T get(int id);
    boolean update(T entity);
    void delete(int id);
    List<T> get_all_entity();
}
