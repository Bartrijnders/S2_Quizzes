package sevices;

public class PasswordService {

    public boolean check(String password){
        //at least 1 lowercase, 1 uppercase, 1 digit. between 6 and 100 characters long
        String regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,100}$";
        return password.matches(regexp);
    }
}
