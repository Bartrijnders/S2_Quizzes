package sevices;

import com.rijnders.dao.Dao;
import com.rijnders.dao.UserDao;
import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;

import java.sql.SQLException;

public class UserSaveService implements UserSaveServiceAble {

    private final Dao<User> dao;

    public UserSaveService() throws SQLException {
        dao = new UserDao();
    }

    @Override
    public void registerNewUser(String username, String email, String password) throws SQLException {
        User user = new StandardUser(username, email, password);
        saveUser(user);
    }

    protected void saveUser(User user) throws SQLException {
        dao.save(user);
    }
}
