package sevices;

import com.rijnders.dao.DaoByString;
import com.rijnders.dao.UserDao;
import com.rijnders.entityinterfaces.User;
import messages.RegisteryCheckMessage;

import java.sql.SQLException;

public class RegisterInputService {

    private final DaoByString<User> userDao;

    public RegisterInputService() {
        this.userDao = new UserDao();
    }

    public RegisteryCheckMessage checkInput(String username, String email) throws SQLException {
        boolean usernameUnique = true;
        boolean emailUnique = true;
        User user = userDao.getByEmail(email);
        if(user != null){
            emailUnique = false;
        }
        user = userDao.getByUsername(username);
        if(user != null){
            usernameUnique = true;
        }
        return new RegisteryCheckMessage(usernameUnique, emailUnique);
    }
}
