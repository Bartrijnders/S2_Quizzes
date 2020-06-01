package factories;

import session.QuizzesSession;
import session.SessionAble;

public class SessionFactory implements Factory<SessionAble> {

    @Override
    public SessionAble Create() {
        return new QuizzesSession();
    }
}
