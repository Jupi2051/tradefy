package org.example.OOP;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class sessionsControl {

    private static List<ActiveSession> activeSessionsList = new ArrayList<>();
    public static ActiveSession InitiateSession(User sessionOwner)
    {
        String SessionId = UUID.randomUUID().toString();
        ActiveSession newSession = new ActiveSession(SessionId, sessionOwner);
        return newSession;
    }

    public static boolean TerminateSession(String sessionId)
    {
        return activeSessionsList.removeIf((element) -> element.getSessionId() == sessionId);
    }

    public static boolean TerminateSession(ActiveSession session)
    {
        return activeSessionsList.removeIf((element) -> element.getSessionId() == session.getSessionId());
    }
}
