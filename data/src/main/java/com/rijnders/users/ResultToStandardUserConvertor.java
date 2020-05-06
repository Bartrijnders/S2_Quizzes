package com.rijnders.users;

import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ResultToStandardUserConvertor implements ResultToUserConvertor {

    @Override
    public User convert(ResultSet resultSet) throws SQLException {
        String userIdStr = resultSet.getString("id");
        UUID userId = UUID.fromString(userIdStr);
        String userName = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new StandardUser(userId, userName,email,password);
    }
}
