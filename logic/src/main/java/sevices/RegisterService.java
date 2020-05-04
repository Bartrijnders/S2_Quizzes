package sevices;

import messages.RegisteryCheckMessage;

public class RegisterService {

    private final RegisterInputService registerInputService;
    private final RegisterInsertService registerInsertService;

    public RegisterService() {
        this.registerInputService = new RegisterInputService();
        this.registerInsertService = new RegisterInsertService();
    }

    public RegisteryCheckMessage register(String username, String email, String password){
        RegisteryCheckMessage check = registerInputService.checkInput(username, email);

        if(check.getEmailIsUnique()&&check.getUsernameIsUnique()){
            registerInsertService.registerNewUser(username,email,password);
        }
        return check;
    }
}
