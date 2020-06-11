package com.rijnders.resultconvertors;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultConverter<T> {
    T convert(ResultSet resultSet) throws SQLException;
}
