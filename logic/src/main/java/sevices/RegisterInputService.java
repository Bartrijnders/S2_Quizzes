package sevices;

import com.rijnders.dao.DaoByString;
import com.rijnders.dao.UserDao;
import com.rijnders.entityinterfaces.User;
import messages.RegistryCheckMessage;
import messages.RegistryCheckMessageAble;

import java.sql.SQLException;

public class RegisterInputService implements RegisterInputServiceAble {

    private final DaoByString<User> userDao;

    public RegisterInputService() throws SQLException {
        userDao = new UserDao();
    }

    @Override
    public RegistryCheckMessageAble checkInput(String username, String email) throws SQLException {
        boolean usernameUnique = true;
        boolean emailUnique = true;
        User user = getByEmail(email);
        if (user != null) {
            emailUnique = false;
        }
        user = getByUsername(username);
        if (user != null) {
            usernameUnique = false;
        }
        return new RegistryCheckMessage(usernameUnique, emailUnique);
    }

    protected User getByEmail(String email) throws SQLException {
        return userDao.getByEmail(email);
    }

    protected User getByUsername(String username) throws SQLException {
        return userDao.getByUsername(username);
    }
}
