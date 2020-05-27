package sevices;

import com.rijnders.dao.Dao;
import com.rijnders.dao.UserDao;
import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;

import java.sql.SQLException;

public class RegisterInsertService {

    private final Dao<User> dao;

    public RegisterInsertService() {
        dao = new UserDao();
    }

    public void registerNewUser(String username, String email, String password) throws SQLException {
        User user = new StandardUser(username, email, password);
        dao.save(user);
    }
}
