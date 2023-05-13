package org.example.OOP;

public class ActiveSession {
    private String sessionId;
    private User user;

    ActiveSession(String sessionId, User user)
    {
        this.sessionId = sessionId;
        this.user = user;
    }

    public void setSessionId(String NewId)
    {
        this.sessionId = NewId;
    }

    public String getSessionId()
    {
        return this.sessionId;
    }

    public User getSessionUser()
    {
        return this.user;
    }

    public void terminate()
    {
        sessionsControl.TerminateSession(this); // make the session kill itself
    }
}
