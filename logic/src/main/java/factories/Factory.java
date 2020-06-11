package factories;

import java.sql.SQLException;

public interface Factory<T> {

    T Create() throws SQLException;
}
