package com.rijnders.dao;

import java.sql.SQLException;

public interface DaoByString<T>{

    T getByString(String string) throws SQLException;
}
