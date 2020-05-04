package sevices;

import com.rijnders.entities.StandardUser;
import com.rijnders.entityinterfaces.User;
import com.rijnders.users.InserStandardUser;
import com.rijnders.users.UserInsertable;

public class RegisterInsertService {

    private final UserInsertable userInsertable;

    public RegisterInsertService() {
        userInsertable = new InserStandardUser();
    }

    public int registerNewUser(String username, String email, String password){
        User user = new StandardUser(username, email, password);
        return userInsertable.insert(user);
    }
}
