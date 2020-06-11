package factories;

import com.rijnders.entityinterfaces.User;
import session.SessionAble;

import java.sql.SQLException;

public interface SessionFactoryAble {
    SessionAble create(User user) throws SQLException;
}
