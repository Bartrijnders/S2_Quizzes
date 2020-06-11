package com.rijnders.resultconvertors;

import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ResultToStandardUserConverter implements ResultConverter<User> {


    private static ResultToStandardUserConverter resultToStandardUserConverter;

    private ResultToStandardUserConverter() {
    }

    public static ResultToStandardUserConverter getInstance() {
        if (resultToStandardUserConverter == null) {
            resultToStandardUserConverter = new ResultToStandardUserConverter();
        }
        return resultToStandardUserConverter;
    }

    @Override
    public User convert(ResultSet resultSet) throws SQLException {
        @SuppressWarnings("SpellCheckingInspection") String userIdStr = resultSet.getString("userid");
        UUID userId = UUID.fromString(userIdStr);
        String userName = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new StandardUser(userId, userName,email,password);
    }
}
