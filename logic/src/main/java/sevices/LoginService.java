package sevices;

import com.rijnders.dao.UserDao;
import com.rijnders.entityinterfaces.User;

import java.sql.SQLException;

public class LoginService implements LoginServiceAble {


    @Override
    public User loginWithEmail(String email, String password) throws SQLException {

        UserDao userDao = new UserDao();
        User user = userDao.getByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }


}
