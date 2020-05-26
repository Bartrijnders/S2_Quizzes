package com.rijnders.resultconvertors;

import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;
import com.rijnders.resultconvertors.ResultConvertor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ResultToStandardUserConvertor implements ResultConvertor<User> {


    private static ResultToStandardUserConvertor resultToStandardUserConvertorInstance;

    private ResultToStandardUserConvertor() {
    }

    public static ResultToStandardUserConvertor getInstance() {
        if (resultToStandardUserConvertorInstance == null) {
            resultToStandardUserConvertorInstance = new ResultToStandardUserConvertor();
        }
        return resultToStandardUserConvertorInstance;
    }

    @Override
    public User convert(ResultSet resultSet) throws SQLException {
        String userIdStr = resultSet.getString("userid");
        UUID userId = UUID.fromString(userIdStr);
        String userName = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new StandardUser(userId, userName,email,password);
    }
}
