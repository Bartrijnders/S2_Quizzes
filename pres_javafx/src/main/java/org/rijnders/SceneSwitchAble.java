package org.rijnders;

import session.SessionAble;

public interface SceneSwitchAble {
    void switchTo(SessionAble session, String fxmlFileName);
}
