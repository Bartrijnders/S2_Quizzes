package sevices;

import java.sql.SQLException;

public interface GetByValueService<T, S> {

    S getByValue(T value) throws SQLException;
}
