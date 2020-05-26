package com.rijnders.resultconvertors;

import com.rijnders.entityinterfaces.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultConvertor<T> {
    T convert(ResultSet resultSet) throws SQLException;
}
