package sevices;

import java.sql.SQLException;

public interface DeleteService<T, S> {

    void delete(T toDeleteType, S session) throws SQLException;
}
