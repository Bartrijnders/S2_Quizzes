package com.rijnders.users;

import com.rijnders.entityinterfaces.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultToUserConvertor {
    User convert(ResultSet resultSet) throws SQLException;
}
