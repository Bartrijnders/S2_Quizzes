package factories;

import com.rijnders.entityinterfaces.User;
import session.QuizzesSession;
import session.SessionAble;

import java.sql.SQLException;

public class SessionFactory implements SessionFactoryAble {

    @Override
    public SessionAble create(User user) throws SQLException {
        return new QuizzesSession(user);
    }
}
