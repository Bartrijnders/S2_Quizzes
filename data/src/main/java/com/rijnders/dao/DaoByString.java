package com.rijnders.dao;

import java.sql.SQLException;

public interface DaoByString<T>{

    T getByEmail(String email) throws SQLException;

    T getByUsername(String username) throws SQLException;
}
