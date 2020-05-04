package sevices;

import com.rijnders.entityinterfaces.User;
import com.rijnders.users.GetUserDao;

public class LoginService {

    private final GetUserDao getUserDao;

    public LoginService() {
        getUserDao = new GetUserDao();
    }
    public User loginWithEmail(String email, String password){
        User comp = getUserDao.selectUserByEmail(email);
        if(comp != null && comp.getPassword().equals(password)){
            return comp;
        }
        else{
            return null;
        }
    }


}
