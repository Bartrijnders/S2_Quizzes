package factories;

import sevices.ActiveUserService;

public class ActiveUserServiceFactory implements Factory<ActiveUserService> {

    @Override
    public ActiveUserService Create() {
        return new ActiveUserService();
    }
}
