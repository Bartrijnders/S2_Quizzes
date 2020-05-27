package sevices;

import com.rijnders.dao.Dao;
import com.rijnders.dao.DaoByString;
import com.rijnders.dao.UserDao;
import com.rijnders.entityinterfaces.User;
import com.rijnders.users.SelectUserDao;

public class LoginService {


    public LoginService() {
    }
    public User loginWithEmail(String email, String password){

        UserDao userDao = new UserDao();
        User comp = userDao.getByString(email);
        if(comp != null && comp.getPassword().equals(password)){
            return comp;
        }
        else{
            return null;
        }
    }


}
