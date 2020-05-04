package messages;

public class RegisteryCheckMessage {
    private Boolean usernameIsUnique;
    private Boolean emailIsUnique;

    public RegisteryCheckMessage(Boolean usernameIsUnique, Boolean emailIsUnique) {
        this.usernameIsUnique = usernameIsUnique;
        this.emailIsUnique = emailIsUnique;
    }

    public Boolean getUsernameIsUnique() {
        return usernameIsUnique;
    }

    public Boolean getEmailIsUnique() {
        return emailIsUnique;
    }
}
