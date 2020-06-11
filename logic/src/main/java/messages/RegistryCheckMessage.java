package messages;

public class RegistryCheckMessage implements RegistryCheckMessageAble {
    private final Boolean usernameIsUnique;
    private final Boolean emailIsUnique;

    public RegistryCheckMessage(Boolean usernameIsUnique, Boolean emailIsUnique) {
        this.usernameIsUnique = usernameIsUnique;
        this.emailIsUnique = emailIsUnique;
    }

    @Override
    public Boolean getUsernameIsUnique() {
        return usernameIsUnique;
    }

    @Override
    public Boolean getEmailIsUnique() {
        return emailIsUnique;
    }
}
