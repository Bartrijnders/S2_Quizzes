package session;

import sevices.ActiveUserService;

public interface ActiveUserServiceAble {

    ActiveUserService getActiveUserService();

    void setActiveUserService(ActiveUserService activeUserService);
}
