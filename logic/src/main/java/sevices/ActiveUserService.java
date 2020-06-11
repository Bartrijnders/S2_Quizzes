package sevices;

import com.rijnders.entityinterfaces.User;
import session.ActiveUserManageAble;

public final class ActiveUserService implements ActiveUserManageAble {

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
