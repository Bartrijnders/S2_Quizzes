package sevices;

import com.rijnders.entityinterfaces.User;

import java.sql.SQLException;

public interface LoginServiceAble {
    User loginWithEmail(String email, String password) throws SQLException;
}
