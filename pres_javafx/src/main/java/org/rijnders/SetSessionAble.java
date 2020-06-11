package org.rijnders;

import session.SessionAble;

import java.io.IOException;
import java.sql.SQLException;

public interface SetSessionAble {
    void setSession(SessionAble session) throws IOException, SQLException;
}
