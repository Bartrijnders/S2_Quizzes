package sevices;

import com.rijnders.entityinterfaces.User;
public final class ActiveUserService {

    private User user;

    public ActiveUserService() {
        user = null;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
