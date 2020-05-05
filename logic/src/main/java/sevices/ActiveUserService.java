package sevices;

import com.rijnders.entityinterfaces.User;

public final class ActiveUserService {

    private User user;
    private static ActiveUserService activeUserService;

    private ActiveUserService() {
        user = null;
    }


    public static ActiveUserService getInstance(){
        if(activeUserService == null){
            activeUserService = new ActiveUserService();
        }
        return activeUserService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
