package sevices;

import messages.RegistryCheckMessageAble;

import java.sql.SQLException;

public interface RegisterServiceAble {
    RegistryCheckMessageAble register(String username, String email, String password) throws SQLException;
}
