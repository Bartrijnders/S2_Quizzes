package com.rijnders.dao;

import java.sql.SQLException;
import java.util.UUID;

public interface DaoByParent<T> {
    T getByParent(UUID id) throws SQLException;
}
