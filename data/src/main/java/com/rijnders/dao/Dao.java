package com.rijnders.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface Dao<T> {

    T get(UUID id) throws SQLException;

    List<T> getAll() throws SQLException;

    void save(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(T t) throws SQLException;
}
