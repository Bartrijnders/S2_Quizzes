package sevices;

import messages.RegisteryCheckMessage;
import com.rijnders.entityinterfaces.User;
import com.rijnders.users.GetUserDao;

import java.util.List;

public class RegisterInputService {

    private final GetUserDao getUserDao;

    public RegisterInputService() {
        this.getUserDao = new GetUserDao();
    }

    public RegisteryCheckMessage checkInput(String username, String email){
        List<User> foundUsers = getUserDao.selectUsersByUsername_Email(username,email);
        boolean username_unique;
        boolean email_unique;

        if(foundUsers.stream()
                .filter(e -> e.getUserName().toUpperCase() == username.toUpperCase())
                .findFirst()
                .orElse(null) == null){
            username_unique = true;
        }
        else{
            username_unique = false;
        }
        if(foundUsers.stream()
                .filter(e -> e.getEmail().toUpperCase() == email.toUpperCase())
                .findFirst()
                .orElse(null) == null){
            email_unique = true;
        }
        else{
            email_unique = false;
        }

        return new RegisteryCheckMessage(username_unique, email_unique);
    }
}
