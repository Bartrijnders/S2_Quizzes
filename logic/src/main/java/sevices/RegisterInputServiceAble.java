package sevices;

import messages.RegistryCheckMessageAble;

import java.sql.SQLException;

public interface RegisterInputServiceAble {
    RegistryCheckMessageAble checkInput(String username, String email) throws SQLException;
}
