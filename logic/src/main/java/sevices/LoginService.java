package sevices;

import com.rijnders.entityinterfaces.User;
import com.rijnders.users.SelectUserDao;

public class LoginService {

    private final SelectUserDao selectUserDao;

    public LoginService() {
        selectUserDao = new SelectUserDao();
    }
    public User loginWithEmail(String email, String password){
        User comp = selectUserDao.selectUserByEmail(email);
        if(comp != null && comp.getPassword().equals(password)){
            return comp;
        }
        else{
            return null;
        }
    }


}
