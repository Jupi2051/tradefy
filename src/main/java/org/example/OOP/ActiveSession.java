package org.example.OOP;

public class ActiveSession {
    public final String sessionId;
    public final User user;

    ActiveSession(String sessionId, User user)
    {
        this.sessionId = sessionId;
        this.user = user;
    }
}
