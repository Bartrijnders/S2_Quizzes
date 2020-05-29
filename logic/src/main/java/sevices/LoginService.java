package sevices;

import com.rijnders.dao.UserDao;
import com.rijnders.entityinterfaces.User;

import java.sql.SQLException;

public class LoginService {


    public LoginService() {
    }
    public User loginWithEmail(String email, String password) throws SQLException {

        UserDao userDao = new UserDao();
        User comp = userDao.getByEmail(email);
        if(comp != null && comp.getPassword().equals(password)){
            return comp;
        }
        else{
            return null;
        }
    }


}
