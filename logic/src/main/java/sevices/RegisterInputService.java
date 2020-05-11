package sevices;

import com.rijnders.entityinterfaces.User;
import com.rijnders.users.SelectUserDao;
import messages.RegisteryCheckMessage;

import java.util.List;

public class RegisterInputService {

    private final SelectUserDao selectUserDao;

    public RegisterInputService() {
        this.selectUserDao = new SelectUserDao();
    }

    public RegisteryCheckMessage checkInput(String username, String email){
        List<User> foundUsers = selectUserDao.selectUsersByUsername_Email(username,email);
        boolean username_unique;
        boolean email_unique;

        username_unique = foundUsers != null && foundUsers.stream()
                .filter(e -> e.getUserName().toUpperCase() == username.toUpperCase())
                .findFirst()
                .orElse(null) == null;
        email_unique = foundUsers != null && foundUsers.stream()
                .filter(e -> e.getEmail().toUpperCase() == email.toUpperCase())
                .findFirst()
                .orElse(null) == null;

        return new RegisteryCheckMessage(username_unique, email_unique);
    }
}
