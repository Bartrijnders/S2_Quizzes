package sevices;

import java.sql.SQLException;

public interface SaveService<T, S> {
    void save(T toSave, S session) throws SQLException;
}
