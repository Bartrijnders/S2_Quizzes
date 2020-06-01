package session;

import com.rijnders.entityinterfaces.User;

public interface ActiveUserManageAble {

    User getUser();

    void setUser(User user);
}
