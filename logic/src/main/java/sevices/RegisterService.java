package sevices;

import messages.RegistryCheckMessageAble;

import java.sql.SQLException;

public class RegisterService implements RegisterServiceAble {

    private final RegisterInputServiceAble registerInputServiceAble;
    private final UserSaveServiceAble userSaveServiceAble;

    public RegisterService() throws SQLException {
        this.registerInputServiceAble = new RegisterInputService();
        this.userSaveServiceAble = new UserSaveService();
    }

    @Override
    public RegistryCheckMessageAble register(String username, String email, String password) throws SQLException {
        RegistryCheckMessageAble check = getCheck(username, email);
        if (check.getEmailIsUnique() && check.getUsernameIsUnique()) {
            userSaveServiceAble.registerNewUser(username, email, password);
        }
        return check;
    }

    protected RegistryCheckMessageAble getCheck(String username, String email) throws SQLException {
        return registerInputServiceAble.checkInput(username, email);
    }


}
