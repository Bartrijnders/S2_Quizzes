package sevices;

import java.sql.SQLException;

public interface UserSaveServiceAble {
    void registerNewUser(String username, String email, String password) throws SQLException;
}
